package com.example.trainingapp.createTraining

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.trainingapp.R


class NameTrainingFragment : Fragment(), View.OnClickListener {
    lateinit var nextStep: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_name_training, container, false)
        nextStep = view.findViewById(R.id.toExerciseList)
        nextStep.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.toExerciseList ->{
                view.findNavController().navigate(R.id.exerciseListFragment)
            }
        }
    }


}