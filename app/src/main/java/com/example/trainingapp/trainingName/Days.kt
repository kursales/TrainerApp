package com.example.trainingapp.trainingName

import kotlin.math.pow

class Days() {
    companion object {
        var days: ArrayList<Boolean> = ArrayList()
        fun initDays() {
            for (i in 0..6) {
                days.add(false)
            }
        }
        fun changeDays(id:Int):Boolean{
            days[id] = !days[id]
            return days[id]
        }
        fun resultDays():Int {
            var sum = 0
            for (i in 0..6) {
                if(days[i]){
                    sum += 2.0.pow(i).toInt()
                }

            }
            return sum
        }

    }

}