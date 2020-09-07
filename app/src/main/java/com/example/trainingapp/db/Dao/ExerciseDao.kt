package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.trainingapp.db.Entity.Exercise

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exercise: Exercise)
}