package com.example.trainingapp.db.Entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
data class FieItem(val uri: Uri, val path: String) {

}