package com.example.trainingapp.newexercise

import com.example.trainingapp.db.Entity.ExerciseList
import com.example.trainingapp.db.Entity.FieItem
import com.example.trainingapp.db.Entity.FileImage
import com.example.trainingapp.db.repositories.ExerciseListRepository
import com.example.trainingapp.db.repositories.FileRepository
import java.io.File

class SaveImage {

    companion object{
       suspend fun saveImage(file: File, fileRepository: FileRepository): Long{
            val id = fileRepository.insert(FileImage(file.absolutePath))
           return id
        }

        suspend fun saveExercise(name: String, id: Long, exerciseListRepository: ExerciseListRepository){
            exerciseListRepository.insert(ExerciseList(name, 0, id))
        }
    }
}