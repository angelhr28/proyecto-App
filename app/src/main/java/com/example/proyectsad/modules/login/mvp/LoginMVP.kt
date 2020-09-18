package com.example.proyectsad.modules.login.mvp

import com.example.proyectsad.helper.saveCache.DataStandar
import com.example.proyectsad.modules.login.model.pojo.DataUser
import io.reactivex.Observable

interface LoginMVP{
    interface View {
        fun logInSuccessful()
        fun logInError(msj: String)
        fun showProgress(msj:String)
        fun hideProgress()
        fun showSnackbar(msj:String)
    }
    interface Presenter{
        fun logInStandart(user:String, password:String)
        fun logInRedes(user:String)
        fun recoveryPassword(user: String)
        fun ondestroy()
    }
    interface Model{
        fun logInStandart(user:String, password:String) : Observable<DataUser>
        fun logInRedes(user:String) : Observable<DataUser>
        fun recoveryPassword(user: String) : Observable<DataStandar>
    }
}