package com.example.trainingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trainingapp.db.Dao.ExerciseDao
import com.example.trainingapp.db.Dao.ExerciseListDao
import com.example.trainingapp.db.Dao.TrainingDao
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.Entity.ExerciseList
import com.example.trainingapp.db.Entity.Training

@Database(entities = [ExerciseList::class, Training::class, Exercise::class], version = 1)
abstract class Database:RoomDatabase()   {
    abstract fun exerciseListDao():ExerciseListDao
    abstract fun trainingDao():TrainingDao
    abstract fun exerciseDao():ExerciseDao


    companion object {
        @Volatile private var instance: com.example.trainingapp.db.Database? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            com.example.trainingapp.db.Database::class.java, "database.db")
            .build()
    }
}