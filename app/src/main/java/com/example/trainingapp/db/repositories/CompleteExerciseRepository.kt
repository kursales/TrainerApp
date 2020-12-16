package com.example.trainingapp.db.repositories

import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.CompleteExercise

class CompleteExerciseRepository(database: Database) {
    private  val dao = database.completeExerciseDao()
    suspend fun insert(completeExercise: CompleteExercise){
        dao.insert(completeExercise)
    }
    suspend fun getExercise(name: String): List<CompleteExercise> = dao.getExercise(name)

    suspend fun getAll(): List<CompleteExercise> = dao.getAll()
}