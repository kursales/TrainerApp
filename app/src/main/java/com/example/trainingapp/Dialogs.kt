package com.example.trainingapp

import android.app.AlertDialog
import android.content.Context
import android.widget.TextView
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.thread

object Dialogs {

    fun timerDialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        var time = 10
        val timer = TextView(context)
        val dialog = dialogBuilder.setView(timer)
            .setNegativeButton("Остановить") { dialog, button ->
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
            dialog.cancel()
        }
    }

    fun cancelCreateTraining(context: Context, callback: () -> Unit) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("Exit")
            .setMessage("Cancel create training?")
            .setPositiveButton("Yes") { dialog, button ->
                callback.invoke()
            }
            .setNegativeButton("No") { dialog, button ->
                dialog.cancel()
            }.show()
    }
    fun CreateExercise(context: Context, callback: () -> Unit){
        val dialog = AlertDialog.Builder(context)
            .setTitle("Create Exercise")
            .setMessage("do you want to create another exercise?")
            .setPositiveButton("Yes") { dialog, button ->
                callback.invoke()
            }
            .setNegativeButton("No") { dialog, button ->
                dialog.cancel()
            }.show()
    }
}