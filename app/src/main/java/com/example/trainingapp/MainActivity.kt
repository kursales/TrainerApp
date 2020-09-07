package com.example.trainingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.trainingapp.createTraining.CreateTrainingActivity
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.ExerciseList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var createButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createButton = findViewById(R.id.createTraining)
        createButton.setOnClickListener(this)
     //   downloadData()

    }

    override fun onClick(view:View) {
        startActivity(Intent(this, CreateTrainingActivity::class.java))

    }

    fun downloadData(){
        CoroutineScope(Dispatchers.IO).launch{
            val db = Database.invoke(this@MainActivity)
            val dao = db.exerciseListDao()
            dao.insert(ExerciseList("Отжимания"))
            dao.insert(ExerciseList("Приседания"))
            dao.insert(ExerciseList("Прыжки"))
            dao.insert(ExerciseList("Пресс"))
        }
    }
}