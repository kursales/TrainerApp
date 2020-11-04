package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trainingapp.db.Entity.Training

@Dao
interface TrainingDao {

    @Insert
    suspend fun insert(training: Training): Long

    @Query("SELECT*FROM Training WHERE id = :id")
    suspend fun getTraining(id: Long): Training

    @Query("SELECT*FROM Training WHERE name = :name")
    suspend fun checkName(name: String): List<Training>

    @Query("SELECT*FROM Training")
   suspend fun getAllTrainings(): List<Training>

    @Delete
    suspend fun delete(training: Training)
}
