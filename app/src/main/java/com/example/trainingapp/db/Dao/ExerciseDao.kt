package com.example.trainingapp.db.Dao

import androidx.room.*
import com.example.trainingapp.db.Entity.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT*FROM EXERCISE WHERE training_id = :trainingId")
   suspend fun getTraining(trainingId:Long):List<Exercise>
    @Insert
    suspend fun insert(exercise: Exercise)
    @Delete
    suspend fun delete(exercise: Exercise)
    @Update
    suspend fun update(exercise: Exercise)
}