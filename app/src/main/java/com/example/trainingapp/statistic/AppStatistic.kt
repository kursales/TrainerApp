package com.example.trainingapp.statistic

import com.example.trainingapp.db.Entity.CompleteExercise
import com.example.trainingapp.db.repositories.CompleteExerciseRepository
import com.example.trainingapp.db.repositories.NameTrainingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AppStatistic(val completeExerciseRepository: CompleteExerciseRepository, val nameTrainingRepository: NameTrainingRepository) {
    lateinit var allExercisees: List<CompleteExercise>
    var countList = mutableListOf(0, 0, 0, 0)
    var max = 0
    var executeExercises = 0



    suspend fun getExecuteExercises(): Int {
        allExercisees = completeExerciseRepository.getAll()
        executeExercises = allExercisees.size
        return executeExercises
    }

    fun getFavoriteExercise(): Int {
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

   suspend fun getTrainingCount(): Int{
        return nameTrainingRepository.getAllTrainings().size
    }

    fun getMaxCount(): Pair<String, Int> {
        var maxCount = 0
        var key = ""
        allExercisees.forEach {
            if(it.count> maxCount){
                maxCount = it.count
                key = it.name
            }
        }
        return Pair(key, maxCount)
    }
    fun getAllCount(): Int{
        var sum = 0
        allExercisees.forEach {
            sum += it.count
        }
        return sum
    }
    fun getAverageCount(): Int{
       return if(allExercisees.isNotEmpty()){
            getAllCount()/allExercisees.size
        }else{
           0
       }
    }

}