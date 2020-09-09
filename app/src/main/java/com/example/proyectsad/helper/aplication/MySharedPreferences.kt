package com.example.proyectsad.helper.aplication

import android.content.Context

class MySharedPreferences(context: Context) {

    private val fileName = "smiledu_preferences"

    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    var user: String
        get() = prefs.getString("user", "") ?: ""
        set(value) = prefs.edit().putString("user", value).apply()

    var password: String
        get() = prefs.getString("password", "")  ?: ""
        set(value) = prefs.edit().putString("password", value).apply()

    var deviceToken: String
        get() = prefs.getString("device_token", "")  ?: ""
        set(value) = prefs.edit().putString("device_token", value).apply()

    fun clear() {

        prefs.edit().remove("user").apply()
        prefs.edit().remove("password").apply()
        prefs.edit().remove("device_token").apply()
    }
}