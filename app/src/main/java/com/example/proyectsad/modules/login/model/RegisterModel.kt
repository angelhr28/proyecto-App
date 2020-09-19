package com.example.proyectsad.modules.login.model

import com.example.proyectsad.helper.aplication.getConexionRetrofit
import com.example.proyectsad.modules.login.model.pojo.DataUser
import com.example.proyectsad.modules.login.model.service.LoginService
import com.example.proyectsad.modules.login.model.service.RegisterService
import com.example.proyectsad.modules.login.mvp.RegisterMVP
import com.example.proyectsad.modules.login.presenter.RegisterPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.format.DecimalStyle.STANDARD

class RegisterModel:RegisterMVP.Model {

    private val TAG = LoginModel::class.java.name
    private val retrofit = getConexionRetrofit()
    private val service = retrofit.create(RegisterService::class.java)

    override fun signUpStandard( username: String, email: String, password: String ): Observable<DataUser> {
        return service.register(username, email, password, TypeRegister.STANDARD)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
//@Pendiente LAS CLASES SOLO SE CREAN UNA VEZ NO ES NECESARIO QUE LA VUELVAS A CREAR >:V
enum class TypeRegister {
    STANDARD, SOCIAL_MEDIA
}