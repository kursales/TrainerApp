package com.example.trainingapp.db.repositories

import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.CompleteExercise
import com.example.trainingapp.db.Entity.FieItem
import com.example.trainingapp.db.Entity.FileImage

class FileRepository (database: Database) {
    private  val dao = database.fileDao()
    suspend fun insert(fie: FileImage) = dao.insert(fie)


}