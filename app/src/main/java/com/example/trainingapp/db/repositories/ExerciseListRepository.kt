package com.example.trainingapp.db.repositories

import com.example.trainingapp.core.toArrayList
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.ExerciseList

class ExerciseListRepository(private val database: Database) {
    private val dao = database.exerciseListDao()

    suspend fun getAll() = dao.getAll().toArrayList()

    suspend fun insert(exerciseList: ExerciseList) {
        dao.insert(exerciseList)
    }

}