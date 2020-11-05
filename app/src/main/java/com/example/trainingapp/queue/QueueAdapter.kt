package com.example.trainingapp.queue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.Exercise

public class QueueAdapter(
    val adapterList: ArrayList<Exercise>,
    val callback: (Exercise) -> Unit,
    val callback2: (Exercise) -> Unit
) : RecyclerView.Adapter<QueueHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.queue_item, parent, false)
        return QueueHolder(view) { position ->
          callback2.invoke(adapterList[position])

        }
    }

    override fun onBindViewHolder(holder: QueueHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int {
        return adapterList.size

    }

    fun swapItems(from: Int, to: Int) {
        if (from < to) {
            for (i in from until to) {
                adapterList[i] = adapterList.set(i + 1, adapterList[i])

            }
        } else {
            for (i in from..to + 1) {
                adapterList[i] = adapterList.set(i - 1, adapterList[i])
            }
        }
        notifyItemMoved(from, to)
    }

    fun onDismiss(position: Int) {
        callback.invoke(adapterList[position])
        adapterList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun addItem(exercise: Exercise){
        adapterList.add(exercise)
        notifyItemInserted(adapterList.size)

    }

}