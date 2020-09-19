package com.example.proyectsad.helper.saveCache

import android.content.Context

class MySharedPreferences(context: Context) {

    private val fileName = "masiquy_preferences"
    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    var user: String
        get() = prefs.getString("user", "") ?: ""
        set(value) = prefs.edit().putString("user", value).apply()

    var fotoUser: String
        get() = prefs.getString("foto_user", "")  ?: ""
        set(value) = prefs.edit().putString("foto_user", value).apply()

    var nameCompletoUser: String
        get() = prefs.getString("name_completo_User", "")  ?: ""
        set(value) = prefs.edit().putString("name_completo_User", value).apply()

    var emailUser: String  //@Pendiente borra esta mrd el correo es el User entonces donde cree que va una pista  User :v
        get() = prefs.getString("email_user", "")  ?: ""
        set(value) = prefs.edit().putString("email_user", value).apply()

    var idPersona : Int
        get() = prefs.getInt("id_persona", 0)
        set(value) = prefs.edit().putInt("id_persona", value).apply()

    var soundsEnable: Boolean
        get() = prefs.getBoolean("sounds_enable", true)
        set(value) = prefs.edit().putBoolean("sounds_enable", value).apply()

    var deviceToken: String
        get() = prefs.getString("device_token", "")  ?: ""
        set(value) = prefs.edit().putString("device_token", value).apply()

    var token: String
        get() = prefs.getString("token", "") ?: ""
        set(value) = prefs.edit().putString("token", value).apply()

    fun clear() {
        prefs.edit().remove("user").apply()
        prefs.edit().remove("foto_user").apply()
        prefs.edit().remove("name_completo_User").apply()
        prefs.edit().remove("email_user").apply()
        prefs.edit().remove("id_persona").apply()
        prefs.edit().remove("sounds_enable").apply()
        prefs.edit().remove("device_token").apply()
        prefs.edit().remove("token").apply()
    }
}