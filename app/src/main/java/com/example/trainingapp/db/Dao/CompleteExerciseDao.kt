package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.trainingapp.db.Entity.CompleteExercise

@Dao
interface CompleteExerciseDao {
    @Insert
    suspend fun insert(completeExercise: CompleteExercise)

}