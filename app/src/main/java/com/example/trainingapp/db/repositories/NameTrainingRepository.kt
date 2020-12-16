package com.example.trainingapp.db.repositories

import com.example.trainingapp.db.Dao.TrainingDao
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Training


class NameTrainingRepository(private val db: Database) {
   private val dao: TrainingDao = db.trainingDao()

    suspend fun createTraining(name: String, days: Int): Long{
       return dao.insert(Training(name, days))
    }

    suspend fun checkName(name:String): Boolean{
        val list = dao.checkName(name)
       return list.isEmpty()
    }

    suspend fun delete(training: Training){
        dao.delete(training)
    }
    suspend fun getTraining(id: Long) = dao.getTraining(id)

    suspend fun getAllTrainings() = dao.getAllTrainings()
}