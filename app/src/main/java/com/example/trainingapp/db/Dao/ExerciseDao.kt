package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trainingapp.db.Entity.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT*FROM EXERCISE WHERE training_id = :trainingId")
    fun getTraining(trainingId:Long):List<Exercise>
    @Insert
    suspend fun insert(exercise: Exercise)
}