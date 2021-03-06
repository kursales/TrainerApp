package com.example.trainingapp.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.trainingapp.R
import com.example.trainingapp.core.Prefs
import com.example.trainingapp.core.TrainingApp
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.ExerciseList
import com.example.trainingapp.db.repositories.ExerciseListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment(), View.OnClickListener {
    lateinit var createTraining: Button
    lateinit var chooseTraining: Button
    lateinit var statisticButton: Button
    lateinit var newExercise: Button

    @Inject
    lateinit var exerciseListRepository: ExerciseListRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        createTraining = view.findViewById(R.id.createTraining)
        chooseTraining = view.findViewById(R.id.chooseTraining)
        statisticButton = view.findViewById(R.id.statisticTraining)
        newExercise = view.findViewById(R.id.newExerciseActivity)

        createTraining.setOnClickListener(this)
        chooseTraining.setOnClickListener(this)
        statisticButton.setOnClickListener(this)
        newExercise.setOnClickListener(this)

        if(Prefs.firstStart()) {
            TrainingApp.component.injectMainFragment(this)
            downloadData()
        }
        return view
    }

 private fun downloadData() {
        CoroutineScope(Dispatchers.IO).launch {
            exerciseListRepository.insert(ExerciseList("Отжимания", R.drawable.otjimaniya,null))
            exerciseListRepository.insert(ExerciseList("Приседания", R.drawable.prisedaniya,null))
            exerciseListRepository.insert(ExerciseList("Прыжки", R.drawable.prijki,null))
            exerciseListRepository.insert(ExerciseList("Пресс", R.drawable.press,null))
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.createTraining -> {
                view.findNavController().navigate(R.id.nameTrainingFragment)
            }
            R.id.chooseTraining -> {
                view.findNavController().navigate(R.id.chooseTrainingFragment)
            }
            R.id.statisticTraining ->{
                view.findNavController().navigate(R.id.statisticFragment)
            }
            R.id.newExerciseActivity ->{
                view.findNavController().navigate(R.id.newExerciseFragment)
            }
        }
    }

}
