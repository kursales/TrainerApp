package com.example.trainingapp.db.Entity

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Exercise(val image:Int, val name:String, val training_id:Long, var queue:Int,val path: String?) {
    @PrimaryKey(autoGenerate = true)
    var id = 0L
}