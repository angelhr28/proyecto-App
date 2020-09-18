package com.example.proyectsad.modules.login.mvp

interface RegisterMVP {

    interface View{
        fun showError(msgError : String)
        fun showProgress()
        fun hideProgress()
        fun signUp()
        fun navigationToSignIn()
        fun navigationToIntro()
    }

    interface Presenter{
        fun checkEmptyInputs(text : String):Boolean
        fun checkValidEmail(email : String):Boolean
        fun signUpUser(username : String, email : String, password : String)
    }

    interface Model{

    }


}