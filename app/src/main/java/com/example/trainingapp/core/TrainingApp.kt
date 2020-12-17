package com.example.trainingapp.core

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes

inline fun TextView.addTextChangedListener(
    crossinline beforeTextChanged: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) -> Unit = { _, _, _, _ -> },
    crossinline onTextChanged: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) -> Unit = { _, _, _, _ -> },
    crossinline afterTextChanged: (text: Editable?) -> Unit = {}
): TextWatcher {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s)
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged.invoke(text, start, count, after)
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(text, start, before, count)
        }
    }
    addTextChangedListener(textWatcher)

    return textWatcher
}
fun View.findView(@IdRes id: Int) : View {
    return findViewById(id)
}

fun View.makeGone()  {
    this.visibility = View.GONE
}

fun <T> List<T>.toArrayList(): ArrayList<T>{
    return ArrayList(this)
}

class TrainingApp : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.create()
    }

    companion object {
        private lateinit var instance: TrainingApp
        lateinit var component: AppComponent

        fun getApplicationContext() = instance.applicationContext

    }

}
