package com.example.trainingapp.chooseTraining

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.Training

class ChooseAdapter (val adapterList: ArrayList<Training>,val callback: (Training) -> Unit): RecyclerView.Adapter<ChooseHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_training, parent, false)
        return ChooseHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseHolder, position: Int) {
        holder.bind(adapterList[position], callback)
    }

    override fun getItemCount(): Int {
       return adapterList.size
    }
}