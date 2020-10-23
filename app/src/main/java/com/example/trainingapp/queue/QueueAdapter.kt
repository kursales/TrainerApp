package com.example.trainingapp.queue

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.ExerciseList

public class QueueAdapter(val adapterList:ArrayList<ExerciseList>, val context: Context): RecyclerView.Adapter<QueueHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.queue_item, parent,false)
        return QueueHolder(view)
    }

    override fun onBindViewHolder(holder: QueueHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int {
        return adapterList.size-1

    }

}