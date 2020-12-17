package com.example.trainingapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.trainingapp.db.Entity.FieItem
import com.example.trainingapp.db.Entity.FileImage

@Dao
interface FileDao {
    @Insert
    suspend fun insert(fie: FileImage): Long
}