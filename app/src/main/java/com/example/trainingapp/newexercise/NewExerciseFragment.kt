package com.example.trainingapp.newexercise

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.trainingapp.R
import com.example.trainingapp.core.TrainingApp
import com.example.trainingapp.db.Entity.ExerciseList
import com.example.trainingapp.db.repositories.ExerciseListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NewExerciseFragment : Fragment() {
    lateinit var image: ImageView
    lateinit var editImage: ImageView
    lateinit var editName: EditText
    lateinit var createExercise: Button
    var id = ""


    @Inject
    lateinit var exerciseListRepository: ExerciseListRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TrainingApp.component.injectNewExercisefragment(this)
        return inflater.inflate(R.layout.fragment_new_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editImage = view.findViewById(R.id.changeImage)
        image = view.findViewById(R.id.newExerciseImage)
        editName = view.findViewById(R.id.newExerciseName)
        createExercise = view.findViewById(R.id.createExercise)

        editImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                requireActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                requireActivity().requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0
                )
            } else {
                FilesDialog(requireContext(), 200f) { file ->
                    id = file.absolutePath
                    image.setImageBitmap(BitmapFactory.decodeFile(id))

                }.show()
            }
        }

        createExercise.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                if (editName.text.toString() != "" && checkName()) {
                    exerciseListRepository.insert(ExerciseList(editName.text.toString(), R.drawable.ic_launcher_foreground, id))
                    withContext(Dispatchers.Main){
                        findNavController().navigate(R.id.mainFragment)
                    }
                }else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(), "Введите другое имя", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    suspend fun checkName(): Boolean {
        val list = exerciseListRepository.getAll()
        list.forEach {
            if (it.name == editName.text.toString()) {
                return false
            }
        }
        return true
    }
}