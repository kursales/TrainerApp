package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trainingapp.db.Entity.CompleteExercise

@Dao
interface CompleteExerciseDao {
    @Insert
    suspend fun insert(completeExercise: CompleteExercise)

    @Query("SELECT*FROM COMPLETEEXERCISE WHERE name = :name")
    suspend fun getExercise(name: String): List<CompleteExercise>

    @Query("SELECT*FROM COMPLETEEXERCISE")
    suspend fun getAll():List<CompleteExercise>
}