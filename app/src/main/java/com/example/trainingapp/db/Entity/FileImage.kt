package com.example.trainingapp.db.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class FileImage(val path: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0L
}