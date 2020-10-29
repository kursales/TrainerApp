package com.example.trainingapp.queue

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.Entity.ExerciseList

public class QueueAdapter(val adapterList:ArrayList<Exercise>): RecyclerView.Adapter<QueueHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.queue_item, parent,false)
        return QueueHolder(view)
    }

    override fun onBindViewHolder(holder: QueueHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int {
        return adapterList.size

    }
    fun swapItems(from: Int, to: Int){
        if(from<to){
            for (i in from..to-1){
                adapterList[i] = adapterList.set(i+1, adapterList[i])

            }
        }else{
            for (i in from..to+1){
                adapterList[i] = adapterList.set(i-1, adapterList[i])
            }
        }
        notifyItemMoved(from, to)
    }
    fun onDismiss(position: Int){
        adapterList.removeAt(position)
        notifyItemRemoved(position)
    }

}