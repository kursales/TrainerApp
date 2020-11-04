package com.example.trainingapp.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.trainingapp.Dialogs
import com.example.trainingapp.R
import com.example.trainingapp.db.Dao.CompleteExerciseDao
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.CompleteExercise
import com.example.trainingapp.db.Entity.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class TrainingFragment : Fragment(), View.OnClickListener {
    var trainingId = 0L
    var count = 0
    var cursor = 0
    lateinit var reduceButton: Button
    lateinit var enlargeButton: Button
    lateinit var comleteButton: Button
    lateinit var exerciseName: TextView
    lateinit var traingList: ArrayList<Exercise>
    lateinit var exercise: Exercise
    lateinit var dao: CompleteExerciseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trainingId = requireArguments().getLong("training_id")

        val view = inflater.inflate(R.layout.fragment_training, container, false)
        comleteButton = view.findViewById(R.id.CompleteExercise)
        enlargeButton = view.findViewById(R.id.EnlargeCount)
        reduceButton = view.findViewById(R.id.ReduceCount)
        exerciseName = view.findViewById(R.id.TrainingFragment_ExerciseName)
        comleteButton.setOnClickListener(this)
        enlargeButton.setOnClickListener(this)
        reduceButton.setOnClickListener(this)

        CoroutineScope(Dispatchers.IO).launch {
            dao = Database.invoke(view.context).completeExerciseDao()
            traingList = getTraining()
            withContext(Dispatchers.Main){
                initExercise()

            }

        }





        return view
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
                if (cursor < traingList.size-1) {
          //          saveData(exercise)
                    cursor++
                    Dialogs.timerDialog(requireContext())
                    initExercise()

                }else{
                    v.findNavController().navigate(R.id.mainFragment)
                }
            }
        }
    }

    fun initExercise() {
        traingList.forEach { exercise ->
            if (exercise.queue == cursor) {
                this.exercise = exercise
                count = 10
                exerciseName.text = exercise.name
                comleteButton.text = "$count раз \n выполнено"
            }
        }

    }
    fun saveData(exercise: Exercise){
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(CompleteExercise( exercise.image,exercise.name,count, Date().toString()))
        }
    }
}