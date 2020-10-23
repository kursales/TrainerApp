package com.example.trainingapp.queue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.ExerciseList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class QueueFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: QueueAdapter
    lateinit var exerciseList: ExerciseList
    var trainingId = 0L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_queue, container, false)
        recyclerView = view.findViewById(R.id.queueRecycler)
        trainingId = requireArguments().getLong("training_id")

        return view
    }
    fun getTraining(){
        CoroutineScope(Dispatchers.IO).launch {
            
        }
    }


}