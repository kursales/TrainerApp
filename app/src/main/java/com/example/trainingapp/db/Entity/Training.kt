package com.example.trainingapp.db.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Training(val name:String, val days: Int) {
    @PrimaryKey(autoGenerate = true)
    var id = 0L
}