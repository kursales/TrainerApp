package com.example.trainingapp.core

import com.example.trainingapp.chooseExercises.ChooseExercisesFragment
import com.example.trainingapp.mainFragment.MainFragment
import com.example.trainingapp.queue.QueueFragment
import com.example.trainingapp.training.TrainingFragment
import com.example.trainingapp.trainingName.TrainingNameFragment
import dagger.Component

@Component(modules = [RoomModule::class])
interface AppComponent {
    fun injectNameFragment(fragment: TrainingNameFragment)
    fun injectChooseExerciseFragment(fragment: ChooseExercisesFragment)
    fun injectMainFragment(fragment: MainFragment)
    fun injectQueueFragment(fragment: QueueFragment)
    fun injectTainingFrafment(fragment: TrainingFragment)
}