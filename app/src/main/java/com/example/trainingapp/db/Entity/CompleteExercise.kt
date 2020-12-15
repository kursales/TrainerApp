package com.example.trainingapp.db.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class CompleteExercise(val image:Int, val name:String, val count:Int,val training_id: Long, val date:String){
    @PrimaryKey(autoGenerate = true)
    var id = 0L
}