package com.example.proyectsad.modules.login.model

import android.os.Build
import android.util.Log
import com.example.proyectsad.helper.aplication.*
import com.example.proyectsad.helper.saveCache.DataStandar
import com.example.proyectsad.modules.login.model.pojo.DataUser
import com.example.proyectsad.modules.login.model.service.LoginService
import com.example.proyectsad.modules.login.mvp.LoginMVP
import com.example.proyectsad.root.ctx
import com.example.proyectsad.root.preferences
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginModel : LoginMVP.Model{

    private val TAG = LoginModel::class.java.name
    private val retrofit = getConexionRetrofit()
    private val service = retrofit.create(LoginService::class.java)

    override fun logInStandart(user: String, password: String): Observable<DataUser> {
        return service.login(user, password, TypeLogin.STANDART)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun logInRedes(user: String): Observable<DataUser> {
        return service.login(user, null, TypeLogin.REDES)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun recoveryPassword(user: String): Observable<DataStandar> {
        return service.recoveryPassword(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
}

enum class TypeLogin {
    STANDART, REDES
}