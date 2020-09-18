package com.example.proyectsad.modules.login.model.pojo

import com.google.gson.annotations.SerializedName

data class Usuario (
    @field:SerializedName("nombre_abvr")
    var nombreAbvr: String? = null,
    @field:SerializedName("foto_usuario")
    var fotoUsuario: String? = null,
    @field:SerializedName("id_usuario")
    val idUsuario: Int? = null,
    @field:SerializedName("token")
    val token: String? = null,
    @field:SerializedName("usuario")
    val usuario: String? = null
)

