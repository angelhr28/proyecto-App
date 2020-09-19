package com.example.proyectsad.modules.login.mvp

interface RegisterMVP {

    interface View{
        fun showError(msgError: String)
        fun showProgress()
        fun hideProgress()
        fun signUpSucess()
        fun signUpFailure(msgFailure: String)
        fun navigationToSignIn()
        fun navigationToIntro()
    }

    interface Presenter{
        fun signUp(username : String, email : String, password : String)
        fun onDestroy()
    }

    interface Model{

    }


}