package com.example.trainingapp

import android.app.AlertDialog
import android.content.Context
import android.widget.TextView
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.thread

object Dialogs {

    fun timerDialog(context: Context,callback: () -> Unit) {
        val dialogBuilder = AlertDialog.Builder(context)
        var time = 10
        val timer = TextView(context)
        val dialog = dialogBuilder.setView(timer)
            .setNegativeButton("Остановить") { dialog, button ->
                callback.invoke()
                dialog.cancel()
            }.show()
        CoroutineScope(Dispatchers.IO).launch {
            while (time > 0) {
                time--
                delay(1000L)
                withContext(Dispatchers.Main) {
                    timer.text = "00:$time"
                }
            }
            callback.invoke()
            dialog.cancel()
        }
    }

    fun cancelCreateTraining(context: Context, callback: () -> Unit) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Выход")
            .setMessage("Отменить создание тренировки?")
            .setPositiveButton("Да") { dialog, button ->
                callback.invoke()
            }
            .setNegativeButton("Нет") { dialog, button ->
                dialog.cancel()
            }.show()
    }
    fun CreateExercise(context: Context, callback: () -> Unit){
        val dialog = AlertDialog.Builder(context)
            .setTitle("Создать упражнение")
            .setMessage("Хочешь создать еще одно упражнение?")
            .setPositiveButton("Да") { dialog, button ->
                callback.invoke()
            }
            .setNegativeButton("Нет") { dialog, button ->
                dialog.cancel()
            }.show()
    }
}