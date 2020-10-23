package com.example.trainingapp.trainingName

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.trainingapp.R
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Training
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NameTrainingFragment : Fragment(), View.OnClickListener {
    lateinit var nextStep: Button
    lateinit var monday:Button
    lateinit var tuesday:Button
    lateinit var wednesday:Button
    lateinit var thursday:Button
    lateinit var friday:Button
    lateinit var saturday:Button
    lateinit var sunday:Button
    lateinit var editName:EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_name_training, container, false)
        nextStep = view.findViewById(R.id.toExerciseList)
        monday = view.findViewById(R.id.monday)
        tuesday = view.findViewById(R.id.tuesday)
        wednesday = view.findViewById(R.id.wednesday)
        thursday = view.findViewById(R.id.thursday)
        friday = view.findViewById(R.id.friday)
        saturday = view.findViewById(R.id.saturday)
        sunday = view.findViewById(R.id.sunday)
        nextStep.setOnClickListener(this)
        monday.setOnClickListener(this)
        tuesday.setOnClickListener(this)
        wednesday.setOnClickListener(this)
        thursday.setOnClickListener(this)
        friday.setOnClickListener(this)
        saturday.setOnClickListener(this)
        sunday.setOnClickListener(this)
        editName = view.findViewById(R.id.editTrainingName)

        Days.initDays()
        return view
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.toExerciseList ->{
                CoroutineScope(Dispatchers.IO).launch {
                    val db = Database.invoke(requireContext())
                    val dao = db.trainingDao()
                        val id = dao.insert(Training(editName.text.toString()))
                    val bundle = Bundle()
                    bundle.putLong("id", id)
                    withContext(Dispatchers.Main){
                        view.findNavController().navigate(R.id.exerciseListFragment, bundle)
                    }
                }


            }
            R.id.monday ->{
                if(Days.changeDays(0)){
                    monday.setBackgroundResource(R.color.daySelected)
                }else{
                    monday.setBackgroundResource(R.color.dayNotSelected)
                }

            }
            R.id.tuesday ->{
                if(Days.changeDays(1)){
                    tuesday.setBackgroundResource(R.color.daySelected)
                }else{
                    tuesday.setBackgroundResource(R.color.dayNotSelected)
                }

            }
            R.id.wednesday ->{
                if(Days.changeDays(2)){
                    wednesday.setBackgroundResource(R.color.daySelected)
                }else{
                    wednesday.setBackgroundResource(R.color.dayNotSelected)
                }

            }
            R.id.thursday ->{
                if(Days.changeDays(3)){
                    thursday.setBackgroundResource(R.color.daySelected)
                }else{
                    thursday.setBackgroundResource(R.color.dayNotSelected)
                }

            }
            R.id.friday ->{
                if(Days.changeDays(4)){
                    friday.setBackgroundResource(R.color.daySelected)
                }else{
                    friday.setBackgroundResource(R.color.dayNotSelected)
                }

            }
            R.id.saturday ->{
                if(Days.changeDays(5)){
                    saturday.setBackgroundResource(R.color.daySelected)
                }else{
                    saturday.setBackgroundResource(R.color.dayNotSelected)
                }

             }
            R.id.sunday ->{
                if(Days.changeDays(6)){
                    sunday.setBackgroundResource(R.color.daySelected)
                }else{
                    sunday.setBackgroundResource(R.color.dayNotSelected)
                }

            }
        }
    }


}