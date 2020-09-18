package com.example.trainingapp.createTraining

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

class CreateAdapter constructor(val exerciseList:ArrayList<ExerciseList>, val context:Context) : RecyclerView.Adapter<CreateAdapter.CreateViewHolder>() {
   val chooseList:ArrayList<ExerciseList> = ArrayList()
    class CreateViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.exerciseName)
        val image = itemView.findViewById<ImageView>(R.id.exerciseImage)
        val check = itemView.findViewById<CheckBox>(R.id.chooseExercise)
        fun bind(exerciseList: ExerciseList, chooseList:ArrayList<ExerciseList>) {
            name.text =  exerciseList.name
            check.setOnClickListener{
                if(check.isChecked){
                    chooseList.add(exerciseList)
                }
                else{
                    chooseList.remove(exerciseList)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.create_training_item,parent,false)
        return CreateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreateViewHolder, position: Int) {
        holder.bind(exerciseList[position],chooseList)
    }

    override fun getItemCount(): Int {
        return  exerciseList.size
    }
}