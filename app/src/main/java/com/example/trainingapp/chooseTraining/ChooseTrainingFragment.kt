package com.example.trainingapp.chooseTraining

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Training
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChooseTrainingFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ChooseAdapter
    lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_choose_training, container, false)
        recyclerView = view.findViewById(R.id.trainingList)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        CoroutineScope(Dispatchers.IO).launch {
            adapter = ChooseAdapter(getTrainingList()){
                bundle = Bundle()
                bundle.putLong("training_id", it.id)
                view.findNavController().navigate(R.id.trainingFragment,bundle)
            }
            withContext(Dispatchers.Main){
                recyclerView.adapter = adapter
            }
        }
        return view
    }
  suspend  fun getTrainingList(): ArrayList<Training> {
        val dao = Database.invoke(requireContext()).trainingDao()
        return  dao.getAllTrainings() as ArrayList
    }

}