package com.example.trainingapp.tuneTraining

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.NavHostFragment
import com.example.trainingapp.R
import com.example.trainingapp.db.Dao.ExerciseDao
import com.example.trainingapp.db.Dao.ExerciseListDao
import com.example.trainingapp.db.Database
import com.example.trainingapp.db.Entity.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class podhodFragment : Fragment(), View.OnClickListener {
    lateinit var exerciseName: TextView
    lateinit var editCount: EditText
    lateinit var editPodhod: EditText
    lateinit var image: ImageView
    lateinit var buttonNext: Button
    lateinit var buttonPrevious: Button
    lateinit var buttonCreate: Button
    lateinit var db: Database
    lateinit var exerciseDao: ExerciseDao
    lateinit var exerciseListDao: ExerciseListDao
    lateinit var exerciseList: ArrayList<Exercise>
    var position = 0
    var trainingId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // val intent = intent
        //trainingId = intent.getLongExtra("trainingId", 0)
        CoroutineScope(Dispatchers.IO).launch {
            db = Database.invoke(requireContext())
            exerciseDao = db.exerciseDao()
            exerciseListDao = db.exerciseListDao()
            exerciseList = exerciseDao.getTraining(trainingId) as ArrayList<Exercise>
            val exerciseData = exerciseListDao.getExercise(exerciseList[position].exercise_id)

            withContext(Dispatchers.Main) {
                initializeData(
                    exerciseData.name,
                    exerciseData.image,
                    exerciseList[position].count,
                    exerciseList[position].podhod
                )
            }
        }

    }

    override fun onClick(view: View) {
        exerciseList[position].count = editCount.text.toString().toInt()
        exerciseList[position].podhod = editPodhod.text.toString().toInt()
        when (view.id) {
            R.id.tuneNext -> {
                position++
                if (position < exerciseList.size) {
                    donloadData(position)

                } else {
                    position = 0
                    donloadData(position)
                }


            }
            R.id.tunePrevious-> {
                position--
                if (position > 0) {
                    donloadData(position)
                } else {
                    position = exerciseList.size -1
                    donloadData(position)
                }

            }
            R.id.tuneButtonCreate ->{
                for (i in 0..exerciseList.size-1) {
                    if(exerciseList[i].count == 0 || exerciseList[i].podhod == 0){
                        Toast.makeText(context, "Поле не может быть равно 0", Toast.LENGTH_SHORT).show()
                        position = i
                        donloadData(position)

                    }else{
                        val navController = NavHostFragment.findNavController(this)
                        navController.navigate(R.id.countFragment)
                    }
                }
            }
        }
    }

    fun initializeData(name: String, icon: Int, count: Int, podhod: Int) {
        exerciseName.text = name
        image.setImageResource(icon)
        editCount.setText("$count")
        editPodhod.setText("$podhod")

    }

    fun donloadData(position: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val exerciseData = exerciseListDao.getExercise(exerciseList[position].exercise_id)
            withContext(Dispatchers.Main) {
                initializeData(
                    exerciseData.name,
                    exerciseData.image,
                    exerciseList[position].count,
                    exerciseList[position].podhod
                )
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        exerciseName = view.findViewById(R.id.tuneName)
        editCount = view.findViewById(R.id.editCount)
        editPodhod = view.findViewById(R.id.editPodhod)
        image = view.findViewById(R.id.tuneImage)
        buttonNext = view.findViewById(R.id.tuneNext)
        buttonPrevious = view.findViewById(R.id.tunePrevious)
        buttonCreate = view.findViewById(R.id.tuneButtonCreate)
        buttonCreate.setOnClickListener(this)
        buttonPrevious.setOnClickListener(this)
        buttonNext.setOnClickListener(this)
        return view
    }

}