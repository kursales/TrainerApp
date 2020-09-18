package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.Entity.ExerciseList

@Dao
interface ExerciseListDao {
    @Query("SELECT*FROM ExerciseList")
suspend fun getAll():List<ExerciseList>

    @Query("SELECT*FROM ExerciseList WHERE id = :exerciseId")
    fun getExercise(exerciseId:Long):ExerciseList
    @Insert
    suspend fun insert(exerciseList: ExerciseList)
}