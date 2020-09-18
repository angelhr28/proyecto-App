package com.example.proyectsad.helper.saveCache

import com.example.proyectsad.modules.login.model.pojo.DataUser

class SessionData {

    var currentUsuario: DataUser? = null

    var listNotificationTipo         = mutableListOf<Pair<Int, String>>()
    var listNotificationChat         = mutableListOf<Pair<Int, String>>()

}