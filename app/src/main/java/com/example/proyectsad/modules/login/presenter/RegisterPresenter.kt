package com.example.proyectsad.modules.login.presenter

import android.text.TextUtils
import android.util.Log
import androidx.core.util.PatternsCompat
import com.example.proyectsad.modules.login.mvp.RegisterMVP

class RegisterPresenter(private val view: RegisterMVP.View):RegisterMVP.Presenter {

    override fun signUp(username: String, email: String, password: String) {
        Log.e("Dates: ","$username $email $password")
    }

    override fun onDestroy() {

    }
}