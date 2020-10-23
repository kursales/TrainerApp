package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trainingapp.db.Entity.Training

@Dao
interface TrainingDao {

  @Insert
  suspend  fun insert(training:Training):Long

    @Query("SELECT*FROM Training WHERE id = :id")
    fun getTraining(id:Long):Training

    @Query("SELECT*FROM Training WHERE name = :name")
    fun checkName(name:String):List<Training>
}