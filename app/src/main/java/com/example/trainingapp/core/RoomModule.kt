package com.example.trainingapp.core

import com.example.trainingapp.db.Database
import com.example.trainingapp.db.repositories.CompleteExerciseRepository
import com.example.trainingapp.db.repositories.ExerciseListRepository
import com.example.trainingapp.db.repositories.ExerciseRepository
import com.example.trainingapp.db.repositories.NameTrainingRepository
import dagger.Module
import dagger.Provides
@Module
class RoomModule {
    @Provides
    fun trainingNameRepository(database: Database): NameTrainingRepository = NameTrainingRepository(database)

    @Provides
    fun exerciseRepository(database: Database): ExerciseRepository = ExerciseRepository(database)

    @Provides
    fun exerciseListRepository(database: Database): ExerciseListRepository = ExerciseListRepository(database)

    @Provides
    fun  completeExerciseRepository(database: Database): CompleteExerciseRepository = CompleteExerciseRepository(database)

    @Provides
    fun getDatabase(): Database{
      return  Database.invoke(TrainingApp.getApplicationContext())
    }
}