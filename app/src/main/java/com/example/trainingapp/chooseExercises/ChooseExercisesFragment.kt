package com.example.trainingapp.chooseExercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.Dialogs
import com.example.trainingapp.R
import com.example.trainingapp.core.Keys
import com.example.trainingapp.core.Prefs
import com.example.trainingapp.core.TrainingApp
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.Entity.ExerciseList
import com.example.trainingapp.db.repositories.ExerciseListRepository
import com.example.trainingapp.db.repositories.ExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ChooseExercisesFragment : Fragment(), View.OnClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ChooseExercisesAdapter
    lateinit var exerciseList: ArrayList<ExerciseList>
    lateinit var nextStep: Button
    var trainingId = 0L

    @Inject
    lateinit var exerciseRepository: ExerciseRepository

    @Inject
    lateinit var exerciseListRepository: ExerciseListRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_list, container, false)
        recyclerView = view.findViewById(R.id.exerciseList)
        nextStep = view.findViewById(R.id.nextStep)

        nextStep.setOnClickListener(this)

        TrainingApp.component.injectChooseExerciseFragment(this)
        trainingId = Prefs.getLong(Keys.TRAINING_ID)

        createAdapter()
        return view
    }

    fun createAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
            exerciseList = exerciseListRepository.getAll()
            adapter = ChooseExercisesAdapter(exerciseList)
            withContext(Dispatchers.Main) {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter
            }
        }
    }

    override fun onClick(view: View) {
        if (adapter.chooseList.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                var queue = 0
                adapter.chooseList.forEach { exercise ->
                    exerciseRepository.insert(Exercise(exercise.image, exercise.name, trainingId, ++queue))
                }
                withContext(Dispatchers.Main) {
                    nextStep.findNavController().navigate(R.id.queueFragment)
                }
            }
        }else{
            Toast.makeText(view.context, "Select Exercises", Toast.LENGTH_SHORT).show()
        }
    }
}