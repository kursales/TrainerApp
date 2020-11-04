package com.example.trainingapp.chooseExercises

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.ExerciseList

class ChooseExercisesAdapter (private val exerciseList:ArrayList<ExerciseList>)
    : RecyclerView.Adapter<ChooseViewHolder>() {
  val chooseList:ArrayList<ExerciseList> = ArrayList()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.create_training_item, parent,false)
        return ChooseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseViewHolder, position: Int) {
       holder.bind(exerciseList[position], chooseList)
    }

    override fun getItemCount(): Int {
        return  exerciseList.size
    }
}