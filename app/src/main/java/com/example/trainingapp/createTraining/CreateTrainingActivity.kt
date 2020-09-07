package com.example.trainingapp.createTraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.Entity.ExerciseList
import com.example.trainingapp.db.Entity.Training
import com.example.trainingapp.tuneTraining.TuneActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateTrainingActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter: CreateAdapter
    lateinit var exerciseList: ArrayList<ExerciseList>
    lateinit var nextStep:Button
    lateinit var db:Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_training)
        recyclerView = findViewById(R.id.exerciseList)
        nextStep = findViewById(R.id.nextStep)
        nextStep.setOnClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this)

        createAdapter()

    }

    fun createAdapter(){
        CoroutineScope(Dispatchers.IO).launch {
            db = Database.invoke(this@CreateTrainingActivity)
            val dao = db.exerciseListDao()
            exerciseList = dao.getAll() as ArrayList<ExerciseList>
            adapter = CreateAdapter(exerciseList, this@CreateTrainingActivity)
            withContext(Dispatchers.Main){
                recyclerView.adapter = adapter
            }
        }

    }

    override fun onClick(view: View) {

      CoroutineScope(Dispatchers.IO).launch{
          val trainingDao = db.trainingDao()
          val exerciseDao = db.exerciseDao()
          val training = Training("training")
          trainingDao.insert(training)
          adapter.chooseList.forEach {
              exerciseDao.insert(Exercise(it.id,training.id,0,0))
          }
          withContext(Dispatchers.Main) {
              startActivity(Intent(this@CreateTrainingActivity, TuneActivity::class.java))
          }
      }

    }
}