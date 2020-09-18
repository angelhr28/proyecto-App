package com.example.proyectsad.helper.saveCache

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.proyectsad.root.ctx
import com.google.gson.Gson

class MasiquySession {

    private val TAG = MasiquySession::class.java.simpleName
    private val KEY = SessionData::class.java.simpleName
    private val gson: Gson = Gson()
    private val preferences: SharedPreferences
    lateinit var values: SessionData

    init {
        this.preferences = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE)
    }

    fun load() {
        try {
            val jsonString = preferences.getString(KEY, "null")
            values = if (jsonString == "null") SessionData() else gson.fromJson(jsonString, SessionData::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "MyError:load", e)
        }
    }

    fun update() {
        try {
            val editor = preferences.edit()
            val jsonString = gson.toJson(values)
            editor.putString(KEY, jsonString)
            editor.apply()
            // editor.commit()
        } catch (e: Exception) {
            Log.e(TAG, "MyError:update", e)
        }
    }

    fun clear() {
        this.values.currentUsuario = null
    }

    companion object {
        private var instance: MasiquySession? = null
        @Synchronized
        fun getInstance(): MasiquySession {
            if (instance == null)
                instance = MasiquySession()
            instance?.load()
            return instance as MasiquySession
        }
    }
}