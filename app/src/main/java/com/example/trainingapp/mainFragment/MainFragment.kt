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

    @Inject
    lateinit var exerciseListRepository: ExerciseListRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        createTraining = view.findViewById(R.id.createTraining)
        chooseTraining = view.findViewById(R.id.chooseTraining)

        createTraining.setOnClickListener(this)
        chooseTraining.setOnClickListener(this)

        if(Prefs.firstStart()) {
            TrainingApp.component.injectMainFragment(this)
            downloadData()
        }
        return view
    }

 private fun downloadData() {
        CoroutineScope(Dispatchers.IO).launch {
            exerciseListRepository.insert(ExerciseList("Отжимания", R.drawable.otjimaniya))
            exerciseListRepository.insert(ExerciseList("Приседания", R.drawable.prisedaniya))
            exerciseListRepository.insert(ExerciseList("Прыжки", R.drawable.prijki))
            exerciseListRepository.insert(ExerciseList("Пресс", R.drawable.press))
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
        }
    }

}
