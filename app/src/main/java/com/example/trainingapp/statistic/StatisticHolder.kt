package com.example.trainingapp.statistic

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R

class StatisticHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.statisticDescription)
    val count: TextView = itemView.findViewById(R.id.statisticCount)

    fun bind(card: Card){
        name.text = card.name
        count.text = "${card.count}"
    }
}