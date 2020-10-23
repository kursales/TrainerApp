package com.example.trainingapp.createTraining

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Exercise
import com.example.trainingapp.db.Entity.ExerciseList
import com.example.trainingapp.db.Entity.Training
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ExerciseListFragment : Fragment(), View.OnClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CreateAdapter
    lateinit var exerciseList: ArrayList<ExerciseList>
    lateinit var nextStep: Button
    lateinit var db: Database
    var trainingId = 0L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_exercise_list, container, false)
        recyclerView = view.findViewById(R.id.exerciseList)
        nextStep = view.findViewById(R.id.nextStep)
        recyclerView.layoutManager = LinearLayoutManager(context)
        nextStep.setOnClickListener(this)
        createAdapter()
        return view
    }

    fun createAdapter(){
        CoroutineScope(Dispatchers.IO).launch {
            db = Database.invoke(requireContext())
            val dao = db.exerciseListDao()
            exerciseList = dao.getAll() as ArrayList<ExerciseList>
            adapter = CreateAdapter(exerciseList, requireContext())
            withContext(Dispatchers.Main){
                recyclerView.adapter = adapter
            }
        }

    }

    override fun onClick(view: View) {
        CoroutineScope(Dispatchers.IO).launch{
            val trainingDao = db.trainingDao()
            val exerciseDao = db.exerciseDao()
            trainingId = requireArguments().getLong("id")
            val training = trainingDao.getTraining(trainingId)
            val bundle = Bundle()
            bundle.putLong("id",trainingId)
            adapter.chooseList.forEach {
                exerciseDao.insert(Exercise(it.id,training.id,0,0))
            }
            withContext(Dispatchers.Main) {
         nextStep.findNavController().navigate(R.id.podhodFragment, bundle)
            }
        }
    }
}