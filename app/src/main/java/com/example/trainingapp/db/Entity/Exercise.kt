package com.example.trainingapp.db.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class Exercise(val exercise_id:Long,val training_id:Long, var count:Int, var podhod:Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0L
}