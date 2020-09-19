package com.example.proyectsad.modules.login.model.service

import com.example.proyectsad.helper.aplication.metadataUser
import com.example.proyectsad.helper.saveCache.DataStandar
import com.example.proyectsad.modules.login.model.TypeRegister
import com.example.proyectsad.modules.login.model.pojo.DataUser
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {

    @POST("Ruta_del_serviciio")
    @FormUrlEncoded
    fun register(@Field("username")  username   : String?       = null,
              @Field("email")        email      : String?       = null,
              @Field("password")     password   : String?       = null,
              @Field("type")         type       : TypeRegister? = null,
              @Field("metadata")     metadata   : String?       = metadataUser()
    ): Observable<DataUser>

}