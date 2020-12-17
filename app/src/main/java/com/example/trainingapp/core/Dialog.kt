package com.example.trainingapp.core

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.example.trainingapp.R

abstract class Dialog(context: Context) {

    private var dialog: AlertDialog? = null
    private val builder: AlertDialog.Builder
    private val view: View

    abstract fun getLayoutId(): Int

    init {
        view = View.inflate(context, this.getLayoutId(), null)
        view.findViewById<View?>(R.id.exit)?.setOnClickListener {
            dismiss()
        }
        builder = AlertDialog.Builder(context).setView(view)
    }

    abstract fun onCreateView(view: View)

    open fun dismiss() {
        dialog?.dismiss()
    }


    fun show() {
        this.onCreateView(view)
        dialog = builder.show()
    }
}