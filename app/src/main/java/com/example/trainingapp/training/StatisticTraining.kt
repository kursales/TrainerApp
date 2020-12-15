package com.example.trainingapp.training

import com.example.trainingapp.db.repositories.CompleteExerciseRepository
import javax.inject.Inject

class StatisticTraining(val completeExerciseRepository: CompleteExerciseRepository) {


    suspend fun getCount(name: String, approach: Int): Int{
        val exerciseList = completeExerciseRepository.getExercise(name)
        var sum = 0
        return if(exerciseList.isEmpty()){
            0
        }else{
            exerciseList.forEach{
              sum += it.count
            }
            val count = sum/exerciseList.size - approach
            if(count<5){
                return sum
            }else {
                return count
            }
        }
    }
    suspend fun getStatistic(name: String, training_id: Long): ArrayList<Int>{
        var sum = 0
        var max = 0
        var count = 0
        val list = ArrayList<Int>()
        val exerciseList = completeExerciseRepository.getExercise(name)
        exerciseList.forEach{
            sum += it.count
            if(max<it.count){
                max = it.count
            }
            if(it.training_id == training_id){
                count++
            }
        }
        if (exerciseList.isEmpty()) {
            list.add(sum)
            list.add(max)
            list.add(count)
        } else {
            list.add(max)
            list.add(sum / exerciseList.size)
            list.add(count)
        }
        return list
    }
}