package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trainingapp.db.Entity.ExerciseList

@Dao
interface ExerciseListDao {
    @Query("SELECT*FROM ExerciseList")
suspend fun getAll():List<ExerciseList>
    @Insert
    suspend fun insert(exerciseList: ExerciseList)
}