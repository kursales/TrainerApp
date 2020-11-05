package com.example.trainingapp.db.repositories

import com.example.trainingapp.core.toArrayList
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Exercise

class ExerciseRepository(private val database: Database) {
    private val dao = database.exerciseDao()

    suspend fun insert(exercise: Exercise): Long {
        return dao.insert(exercise)
    }

    suspend fun getExercise(id: Long): Exercise {
        return dao.getExercise(id)
    }

    suspend fun getTraining(id: Long) = dao.getTraining(id).toArrayList()

    suspend fun deleteExercise(exercise: Exercise) {
        dao.delete(exercise)
    }

    suspend fun update(exercise: Exercise) {
        dao.update(exercise)
    }
}