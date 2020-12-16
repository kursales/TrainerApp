package com.example.trainingapp.training

import android.os.Bundle
import android.os.CountDownTimer
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

class TimerDialog(val callback:() -> Unit): DialogFragment() {
    lateinit var txttimer: TextView
    lateinit var stopTimer: Button
    private var timer: CountDownTimer? = null
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
        txttimer = view.findViewById(R.id.timer)
        stopTimer = view.findViewById(R.id.stopTimer)
        timer = object : CountDownTimer(
            10 * 1000L,
            1000L
        ) {
            override fun onTick(millisUntilFinished: Long) =
                setTxt("Отдыхайте: \n${millisUntilFinished / 1000L} сек.")


            override fun onFinish() {
                callback.invoke()
                dismiss()
            }
        }.start()

        stopTimer.setOnClickListener {
            callback.invoke()
            dismiss() }


    }

    fun setTxt(name: String) {
        txttimer.text = name
    }
}