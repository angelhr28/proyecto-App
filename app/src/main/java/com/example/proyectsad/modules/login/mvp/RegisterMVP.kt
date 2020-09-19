package com.example.proyectsad.modules.login.mvp

import com.example.proyectsad.modules.login.model.pojo.DataUser
import io.reactivex.Observable

interface RegisterMVP {

    interface View{
        fun showError(msgError: String)
        fun showProgress()
        fun hideProgress()
        fun showSnackBar(msg: String)
        fun signUp()
        fun signUpSucess()
        fun signUpFailure(msgFailure: String)
        fun navigationToSignIn()
        fun navigationToIntro()
    }

    interface Presenter{
        fun signUpStandard(username: String, email: String, password: String)
        fun signUpWithSocialMedia(username: String, email: String, password: String)
        fun onDestroy()
    }

    interface Model{
        fun signUpStandard(username: String, email: String, password: String) : Observable<DataUser>
    }


}