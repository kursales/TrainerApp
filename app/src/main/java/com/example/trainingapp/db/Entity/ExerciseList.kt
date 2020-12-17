package com.example.trainingapp.db.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ExerciseList(var name:String, var image:Int, val path: String?){
    @PrimaryKey(autoGenerate = true)
  var id = 0L

}