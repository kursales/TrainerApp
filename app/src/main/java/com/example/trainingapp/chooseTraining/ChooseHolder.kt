package com.example.trainingapp.chooseTraining

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.Training

class ChooseHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.trainingName)
    lateinit var myCallback : Unit

    fun bind(training: Training, callback: (Training) -> Unit){
        name.text = training.name
        itemView.setOnClickListener {
            callback.invoke(training)
        }
    }
}