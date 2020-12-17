package com.example.trainingapp.chooseExercises

import android.graphics.BitmapFactory
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.db.Entity.ExerciseList

class ChooseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById<TextView>(R.id.exerciseName)
    val image = itemView.findViewById<ImageView>(R.id.exerciseImage)
    val check = itemView.findViewById<CheckBox>(R.id.chooseExercise)


    fun bind(exercise: ExerciseList, chooseList:ArrayList<ExerciseList>) {
        name.text =  exercise.name
        if(exercise.path == null) {
            image.setImageResource(exercise.image)
        }else{
            image.setImageBitmap(BitmapFactory.decodeFile(exercise.path))
        }
        check.setOnClickListener{
            if(check.isChecked){
                chooseList.add(exercise)
            }
            else{
                chooseList.remove(exercise)
            }
        }
    }
}