package com.example.trainingapp.core

import android.app.Application


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
