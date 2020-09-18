package com.example.proyectsad.modules.login.presenter

import com.example.proyectsad.R
import com.example.proyectsad.helper.saveCache.DataStandar
import com.example.proyectsad.helper.aplication.isConnected
import com.example.proyectsad.helper.aplication.isNullOrEmpty
import com.example.proyectsad.helper.saveCache.MasiquySession
import com.example.proyectsad.modules.login.model.LoginModel
import com.example.proyectsad.modules.login.model.PrincipalModel
import com.example.proyectsad.modules.login.model.pojo.DataUser
import com.example.proyectsad.modules.login.mvp.LoginMVP
import com.example.proyectsad.modules.login.mvp.PrincipalMVP
import com.example.proyectsad.root.ctx
import com.example.proyectsad.root.preferences
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import retrofit2.HttpException

class PrincipalPresenter(val view: PrincipalMVP.View):PrincipalMVP.Presenter {

    private val TAG = PrincipalPresenter::class.java.name
    private val model = PrincipalModel()
    private val session   : MasiquySession = MasiquySession.getInstance()
    private val compositeDisposable = CompositeDisposable()


    override fun ondestroy() {
        if (compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}