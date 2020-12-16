package com.example.trainingapp.statistic

import com.example.trainingapp.db.Entity.CompleteExercise
import com.example.trainingapp.db.repositories.CompleteExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AppStatistic(val completeExerciseRepository: CompleteExerciseRepository) {
    lateinit var allExercisees: List<CompleteExercise>
    var countList = mutableListOf(0, 0, 0, 0)
    var favoriteEx = 0
    var max = 0
    var executeExercises = 0

    init {
        CoroutineScope(Dispatchers.IO).launch {
            executeExercises = getExecuteExercises()
        }
    }

    suspend fun getExecuteExercises(): Int {
        allExercisees = completeExerciseRepository.getAll()
        return allExercisees.size
    }

    suspend fun getFavoriteExercise(): Int {
        allExercisees.forEach {
            getExerciseCount(it.name)
        }

        countList.forEach {
            if (it > max) {
                max = it
            }
        }
        return max
    }

    fun getFavoriteName(): String {
        for (i in 0 until countList.size){
            if(countList[i]==max){
                return getExerciseName(i)
            }
        }
return String()
    }

    fun getExerciseCount(name: String) {
        when (name) {
            "Отжимания" -> {
                countList[0]++
            }
            "Приседания" -> {
                countList[1]++
            }
            "Прыжки" -> {
                countList[2]++
            }
            "Пресс" -> {
                countList[3]++
            }
        }
    }

    fun getExerciseName(count: Int) :String{
      return  when (count) {
            0 -> "Отжимания"
            1 -> "Приседания"
            2 -> "Прыжки"
            3 -> "Пресс"
          else ->throw IllegalArgumentException()
      }
    }
}