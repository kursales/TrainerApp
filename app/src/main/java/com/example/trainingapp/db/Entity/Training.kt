package com.example.trainingapp.db.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Training(val name:String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0L
}