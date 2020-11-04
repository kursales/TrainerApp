package com.example.trainingapp.core

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    private const val NAME = "PREFS"
    private val preferences: SharedPreferences =
        TrainingApp.getApplicationContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)

    fun put(key: Keys, long: Long) {
        val editor = preferences.edit()
        editor.putLong(key.name, long)
        editor.apply()
    }

    fun put(key: Keys, boolean: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key.name, boolean)
        editor.apply()
    }

    fun getLong(key: Keys, defaultValue: Long = 0L) = preferences.getLong(key.name, defaultValue)
    fun getBoolean(key: Keys, defaultValue: Boolean = false) = preferences.getBoolean(key.name, defaultValue)

    fun firstStart(): Boolean {
        if (getBoolean(Keys.FIRST_START, true)) {
            put(Keys.FIRST_START, false)
            return true
        }
        return false
    }
}