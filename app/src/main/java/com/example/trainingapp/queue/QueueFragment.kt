package com.example.trainingapp.queue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.Entity.ExerciseList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QueueFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: QueueAdapter
    lateinit var exerciseList: ArrayList<Exercise>
    var trainingId = 0L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_queue, container, false)
        recyclerView = view.findViewById(R.id.queueRecycler)
        trainingId = requireArguments().getLong("training_id")
        val manager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = manager
        getTraining()
        val dividerItemDecoration = DividerItemDecoration(requireContext(), manager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)




        return view
    }

    fun getTraining() {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = Database.invoke(requireContext()).exerciseDao()
            exerciseList = dao.getTraining(trainingId) as ArrayList
            withContext(Dispatchers.Main) {
                adapter = QueueAdapter(exerciseList)
                recyclerView.adapter = adapter
                val callback = DragManageAdapter(
                    adapter,
                    requireContext(),
                    ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
                    ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
                )
                val helper = ItemTouchHelper(callback)
                helper.attachToRecyclerView(recyclerView)
            }
        }
    }
}


