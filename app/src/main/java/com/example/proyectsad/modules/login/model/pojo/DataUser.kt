package com.example.proyectsad.modules.login.model.pojo

import com.google.gson.annotations.SerializedName

data class DataUser (
    @field:SerializedName("msj")
    var msj:String? = null,
    @field:SerializedName("status")
    var status:String? = null,
    @field:SerializedName("token")
    val token: String? = null,
    @field:SerializedName("user")
    var usuario: Usuario? = null
)