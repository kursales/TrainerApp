package com.example.trainingapp.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.trainingapp.Dialogs
import com.example.trainingapp.R
import com.example.trainingapp.core.TrainingApp
import com.example.trainingapp.db.Dao.CompleteExerciseDao
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.CompleteExercise
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.repositories.CompleteExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class TrainingFragment : Fragment(), View.OnClickListener {
    var trainingId = 0L
    var count = 0
    var cursor = 1
    lateinit var reduceButton: Button
    lateinit var enlargeButton: Button
    lateinit var comleteButton: Button
    lateinit var exerciseName: TextView
    lateinit var traingList: ArrayList<Exercise>
    lateinit var exercise: Exercise
    lateinit var image: ImageView
    lateinit var statistic: StatisticTraining
    lateinit var maxCount:TextView
    lateinit var averageValue:TextView
    lateinit var countValue:TextView
    lateinit var statisticList: ArrayList<Int>

    @Inject
    lateinit var completeExerciseRepository: CompleteExerciseRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trainingId = requireArguments().getLong("training_id")
        TrainingApp.component.injectTainingFrafment(this)
        statistic = StatisticTraining(completeExerciseRepository)


        CoroutineScope(Dispatchers.IO).launch {
            traingList = getTraining()
            statistic.getCount("отжимания", 2)
            withContext(Dispatchers.Main) {
                initExercise()

            }

        }
        val view = inflater.inflate(R.layout.fragment_training, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        comleteButton = view.findViewById(R.id.CompleteExercise)
        enlargeButton = view.findViewById(R.id.EnlargeCount)
        reduceButton = view.findViewById(R.id.ReduceCount)
        exerciseName = view.findViewById(R.id.TrainingFragment_ExerciseName)
        image = view.findViewById(R.id.trainingImage)
        maxCount = view.findViewById(R.id.maxCount)
        averageValue = view.findViewById(R.id.averageValue)
        countValue = view.findViewById(R.id.countValue)
        comleteButton.setOnClickListener(this)
        enlargeButton.setOnClickListener(this)
        reduceButton.setOnClickListener(this)
        super.onViewCreated(view, savedInstanceState)
    }

    suspend fun getTraining(): ArrayList<Exercise> {
        val dao = Database.invoke(requireContext()).exerciseDao()
        val list = dao.getTraining(trainingId)
        return list as ArrayList
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ReduceCount -> {
                if (count > 0) {
                    count--
                    comleteButton.text = "$count раз \n выполнено"
                }
            }
            R.id.EnlargeCount -> {
                count++
                comleteButton.text = "$count раз \n выполнено"
            }
            R.id.CompleteExercise -> {
                if (cursor < traingList.size - 1) {
                    saveData(exercise)
                    cursor++

                    Dialogs.timerDialog(requireContext()){
                        initExercise()
                    }



                } else {
                    v.findNavController().navigate(R.id.mainFragment)
                }
            }
        }
    }

    fun initExercise() {
        traingList.forEach { exercise ->
            if (exercise.queue == cursor) {
                this.exercise = exercise
                CoroutineScope(Dispatchers.IO).launch {
                    var average = 0
                    traingList.forEach {
                        if(it.name == exercise.name){
                            average++
                        }
                    }
                    count = statistic.getCount(exercise.name, average)
                     statisticList = statistic.getStatistic(exercise.name, trainingId)
                    withContext(Dispatchers.Main) {
                        image.setImageResource(exercise.image)
                        exerciseName.text = exercise.name
                        comleteButton.text = "$count раз \n выполнено"
                        maxCount.text = "Максимальное количество повторений: ${statisticList[0]}"
                        averageValue.text = "Среднее количество повторений: ${statisticList[1]}"
                        countValue.text = "Всего выполнено для данной тренировки: ${statisticList[2]}"

                        if (count == 0) {
                            Toast.makeText(
                                requireContext(),
                                "Сделайте максимальное количество повторений",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }

    }

    fun saveData(exercise: Exercise) {
        CoroutineScope(Dispatchers.IO).launch {
            completeExerciseRepository.insert(
                CompleteExercise(
                    exercise.image,
                    exercise.name,
                    count,
                    trainingId,
                    Date().toString()
                )
            )
        }
    }

}