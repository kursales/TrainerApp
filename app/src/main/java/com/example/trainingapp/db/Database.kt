package com.example.trainingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trainingapp.db.Dao.*
import com.example.trainingapp.db.Entity.*

@Database(entities = [ExerciseList::class, Training::class, Exercise::class, CompleteExercise::class], version = 1)
abstract class Database:RoomDatabase()   {
    abstract fun exerciseListDao():ExerciseListDao
    abstract fun trainingDao():TrainingDao
    abstract fun exerciseDao():ExerciseDao
    abstract fun completeExerciseDao(): CompleteExerciseDao


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