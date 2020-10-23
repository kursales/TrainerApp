package com.example.trainingapp.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.trainingapp.R
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.ExerciseList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(), View.OnClickListener {
    lateinit var createtraining:Button
    lateinit var chooseTraining : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_main, container, false)
        createtraining = view.findViewById(R.id.createTraining)
        createtraining.setOnClickListener(this)
        createtraining = view.findViewById(R.id.chooseTraining)
        createtraining.setOnClickListener(this)
     //   downloadData()
        return view
    }

    fun downloadData(){
        CoroutineScope(Dispatchers.IO).launch{
            val db = Database.invoke(requireContext())
            val dao = db.exerciseListDao()
            dao.insert(ExerciseList("Отжимания",R.drawable.ic_launcher_foreground))
            dao.insert(ExerciseList("Приседания", R.drawable.ic_launcher_foreground))
            dao.insert(ExerciseList("Прыжки", R.drawable.ic_launcher_foreground))
            dao.insert(ExerciseList("Пресс", R.drawable.ic_launcher_foreground))
        }
    }

    override fun onClick(view:View) {
        when(view.id){
            R.id.createTraining -> {
                view.findNavController().navigate(R.id.nameTrainingFragment)
            }
            R.id.chooseTraining -> {
           }
        }
    }

}
