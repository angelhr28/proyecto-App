package com.example.proyectsad.modules.login.presenter

import android.text.TextUtils
import android.util.Log
import androidx.core.util.PatternsCompat
import com.example.proyectsad.modules.login.mvp.RegisterMVP

class RegisterPresenter(private val view: RegisterMVP.View):RegisterMVP.Presenter {


    override fun checkEmptyInputs(text: String): Boolean {
        return TextUtils.isEmpty(text) //Returns true if the string is null or 0-length
    }

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun signUpUser(username: String, email: String, password: String) {
        Log.e("Dates: ","$username $email $password")
    }
}