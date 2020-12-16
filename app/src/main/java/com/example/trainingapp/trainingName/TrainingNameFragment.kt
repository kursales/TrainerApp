package com.example.trainingapp.trainingName

import android.app.Activity
import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.trainingapp.BackPressedListener
import com.example.trainingapp.Dialogs
import com.example.trainingapp.R
import com.example.trainingapp.core.Keys
import com.example.trainingapp.core.Prefs
import com.example.trainingapp.core.TrainingApp
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Training
import com.example.trainingapp.db.repositories.NameTrainingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TrainingNameFragment : Fragment(), View.OnClickListener, BackPressedListener {
    lateinit var nextStep: Button
    lateinit var monday: Button
    lateinit var tuesday: Button
    lateinit var wednesday: Button
    lateinit var thursday: Button
    lateinit var friday: Button
    lateinit var saturday: Button
    lateinit var sunday: Button
    lateinit var editName: EditText

    var trainingId: Long? = null

    @Inject
    lateinit var trainingNameRepository: NameTrainingRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_name_training, container, false)
        nextStep = view.findViewById(R.id.toExerciseList)
        monday = view.findViewById(R.id.monday)
        tuesday = view.findViewById(R.id.tuesday)
        wednesday = view.findViewById(R.id.wednesday)
        thursday = view.findViewById(R.id.thursday)
        friday = view.findViewById(R.id.friday)
        saturday = view.findViewById(R.id.saturday)
        sunday = view.findViewById(R.id.sunday)
        editName = view.findViewById(R.id.editTrainingName)

        nextStep.setOnClickListener(this)
        monday.setOnClickListener(this)
        tuesday.setOnClickListener(this)
        wednesday.setOnClickListener(this)
        thursday.setOnClickListener(this)
        friday.setOnClickListener(this)
        saturday.setOnClickListener(this)
        sunday.setOnClickListener(this)

        TrainingApp.component.injectNameFragment(this)

        Days.initDays()
        return view
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.toExerciseList -> {
                if (Days.resultDays() != 0) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (trainingNameRepository.checkName(editName.text.toString())) {
                            trainingId = trainingNameRepository.createTraining(
                                editName.text.toString(),
                                Days.resultDays()
                            )
                            Prefs.put(Keys.TRAINING_ID, trainingId!!)
                            withContext(Dispatchers.Main) {
                                val keyboard = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                                keyboard.hideSoftInputFromWindow(view.windowToken, 0)
                                view.findNavController().navigate(R.id.exerciseListFragment)
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(view.context, "Тренировка с таким именем уже существует", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(view.context, "Выберите дней тренировки или введите имя", Toast.LENGTH_SHORT).show()
                }
            }
        R.id.monday ->{
            if (Days.changeDays(0)) {
                monday.setBackgroundResource(R.drawable.day_button_background_selected)
            } else {
                monday.setBackgroundResource(R.drawable.day_button_backgroun_unselected)
            }
        }
        R.id.tuesday ->{
            if (Days.changeDays(1)) {
                tuesday.setBackgroundResource(R.drawable.day_button_background_selected)
            } else {
                tuesday.setBackgroundResource(R.drawable.day_button_backgroun_unselected)
            }

        }
        R.id.wednesday ->{
            if (Days.changeDays(2)) {
                wednesday.setBackgroundResource(R.drawable.day_button_background_selected)
            } else {
                wednesday.setBackgroundResource(R.drawable.day_button_backgroun_unselected)
            }

        }
        R.id.thursday ->{
            if (Days.changeDays(3)) {
                thursday.setBackgroundResource(R.drawable.day_button_background_selected)
            } else {
                thursday.setBackgroundResource(R.drawable.day_button_backgroun_unselected)
            }

        }
        R.id.friday ->{
            if (Days.changeDays(4)) {
                friday.setBackgroundResource(R.drawable.day_button_background_selected)
            } else {
                friday.setBackgroundResource(R.drawable.day_button_backgroun_unselected)
            }

        }
        R.id.saturday ->{
            if (Days.changeDays(5)) {
                saturday.setBackgroundResource(R.drawable.day_button_background_selected)
            } else {
                saturday.setBackgroundResource(R.drawable.day_button_backgroun_unselected)
            }

        }
        R.id.sunday ->{
            if (Days.changeDays(6)) {
                sunday.setBackgroundResource(R.drawable.day_button_background_selected)
            } else {
                sunday.setBackgroundResource(R.drawable.day_button_backgroun_unselected)
            }

        }
    }
}

override fun onBackPressed() {
    Dialogs.cancelCreateTraining(requireContext()) {
        if (trainingId != null) {
            CoroutineScope(Dispatchers.IO).launch {
                trainingNameRepository.delete(trainingNameRepository.getTraining(trainingId!!))
                withContext(Dispatchers.Main) {
                    val fragmentManager = requireActivity().supportFragmentManager
                    fragmentManager.popBackStack()
                }
            }
        }
    }
}


}