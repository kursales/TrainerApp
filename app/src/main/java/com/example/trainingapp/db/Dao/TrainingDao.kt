package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.trainingapp.db.Entity.Training

@Dao
interface TrainingDao {
    @Insert
  suspend  fun insert(training:Training)
}