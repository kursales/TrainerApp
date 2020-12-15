package com.example.trainingapp.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.trainingapp.R
import kotlinx.android.synthetic.main.timer_dialog.view.*
import kotlinx.coroutines.*
import java.util.zip.Inflater

class TimerDialog(): DialogFragment() {
    lateinit var timer: TextView
    lateinit var stopTimer: Button
    var time = 10

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.timer_dialog, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timer = view.findViewById(R.id.timer)
        stopTimer = view.findViewById(R.id.stopTimer)
        CoroutineScope(Dispatchers.IO).launch {
            while (time > 0) {
                time--
                delay(1000L)
                withContext(Dispatchers.Main) {
                    timer.text = "00:$time"
                }
            }
            dismiss()
        }
        stopTimer.setOnClickListener {
            dismiss()
        }
    }
}