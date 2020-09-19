package com.example.proyectsad.modules.login.model.service

import com.example.proyectsad.helper.aplication.metadataUser
import com.example.proyectsad.helper.saveCache.DataStandar
import com.example.proyectsad.modules.login.model.Type
import com.example.proyectsad.modules.login.model.pojo.DataUser
import io.reactivex.Observable
import retrofit2.http.*

interface LoginService {

    @POST("Ruta_del_serviciio")
    @FormUrlEncoded
    fun login(@Field("usuario")  usuario    : String?   = null,
              @Field("pass")     password   : String?   = null,
              @Field("type")     tipo       : Type?= null,
              @Field("metadata") metadata   : String?= metadataUser()): Observable<DataUser>

    @POST("Ruta_del_serviciio")
    @FormUrlEncoded
    fun recoveryPassword(@Field("email")    usuario    : String? = null,
                         @Field("metadata") metadata   : String?= metadataUser()): Observable<DataStandar>

}