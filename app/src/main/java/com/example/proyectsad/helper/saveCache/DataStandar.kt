package com.example.proyectsad.helper.saveCache

import com.google.gson.annotations.SerializedName

data class DataStandar(
    @field:SerializedName("msj")
    var msj:String? = null,
    @field:SerializedName("status")
    var status:String? = null
)