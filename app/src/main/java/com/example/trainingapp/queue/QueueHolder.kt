package com.example.trainingapp.queue

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.Entity.ExerciseList
import com.example.trainingapp.db.Entity.Training
import kotlinx.android.synthetic.main.queue_item.view.*

class QueueHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val image = itemView.findViewById<ImageView>(R.id.queueImage)
    val name = itemView.findViewById<TextView>(R.id.queueName)

    fun bind(exerciseList: Exercise){

        name.text = exerciseList.name

    }

}