package com.example.trainingapp.statistic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R

class StatisticAdapter(val adapterList: ArrayList<Card>) : RecyclerView.Adapter<StatisticHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticHolder {
        val view = LayoutInflater.from(parent.context).inflate( R.layout.statistic_item, parent, false)
        return StatisticHolder(view)
    }

    override fun onBindViewHolder(holder: StatisticHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int {
        return  adapterList.size
    }
}