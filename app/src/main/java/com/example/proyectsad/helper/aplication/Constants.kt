package com.example.proyectsad.helper.aplication

object Constants {

    val stage      = "local" //prod / test / local
    val ROOT_PROD  = "https://api.sin_name.com/"
    val ROOT_LOCL = "http://192.168.1.17:3000/" // "localhost:3000/"

    val ROOT_URL = when (stage) {
        "prod" -> ROOT_PROD
        else -> ROOT_LOCL
    }

    val PLATFORM        = "ANDROID"

}