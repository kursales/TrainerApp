package com.example.trainingapp.db.Entity

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class Exercise(val image:Int, val name:String, val training_id:Long, var queue:Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0L
}