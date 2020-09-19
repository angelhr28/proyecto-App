package com.example.proyectsad.modules.login.presenter

import android.text.TextUtils
import android.util.Log
import androidx.core.util.PatternsCompat
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.isConnected
import com.example.proyectsad.helper.aplication.isNullOrEmpty
import com.example.proyectsad.helper.saveCache.DataStandar
import com.example.proyectsad.helper.saveCache.MasiquySession
import com.example.proyectsad.modules.login.model.LoginModel
import com.example.proyectsad.modules.login.model.RegisterModel
import com.example.proyectsad.modules.login.model.pojo.DataUser
import com.example.proyectsad.modules.login.mvp.RegisterMVP
import com.example.proyectsad.root.ctx
import com.example.proyectsad.root.preferences
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import retrofit2.HttpException

class RegisterPresenter(private val view: RegisterMVP.View):RegisterMVP.Presenter {

    private val TAG = LoginPresenter::class.java.name
    private val model       = RegisterModel()
    private val session: MasiquySession = MasiquySession.getInstance()
    private val compositeDisposable = CompositeDisposable()

    override fun signUpStandard(username: String, email: String, password: String) {
        when{
            isNullOrEmpty(username)     -> return view.showError(ctx.getString(R.string.ingrese_nuevo_nombre))
            isNullOrEmpty(email)        -> return view.showError(ctx.getString(R.string.ingrese_nuevo_email))
            isNullOrEmpty(password)     -> return view.showError(ctx.getString(R.string.ingrese_nuevo_clave))
            !isConnected(ctx)           -> return view.showError(ctx.getString(R.string.sin_conexion))
        }
        view.showProgress()
        val disposable = model.signUpStandard(username, email, password).subscribe(
            {result->
                setUserDataCache(result, username, email)
                view.hideProgress()
                view.navigationToIntro()
            },
            {error->
                view.hideProgress()
                if (error is HttpException) {
                    val response = error.response()
                    try {
                        val jObjError = JSONObject(response.errorBody()?.string())
                        val result = Gson().fromJson(jObjError.toString(), DataStandar::class.java)
                        view.showError(result.msj ?: "")
                    } catch (e: Exception) {
                        view.showError("${ctx.getString(R.string.error_service)} -- ${e.message.toString()}")
                    }
                } else {
                    view.showError("${ctx.getString(R.string.error_general)} -- ${error.message.toString()}")
                }
            }
        )
        compositeDisposable.add(disposable)
    }

    override fun signUpWithSocialMedia(username: String, email: String, password: String) {

    }

    override fun onDestroy() {
        if(compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    private fun setUserDataCache(result: DataUser, username: String,email: String){
        preferences.user      = result.usuario?.usuario ?: ""
        preferences.token     = result.usuario?.token ?: ""
        preferences.idPersona = result.usuario?.idUsuario ?: 0
        preferences.nameCompletoUser  = result.usuario?.nombreAbvr ?: username
        preferences.emailUser         = result.usuario?.emailUser ?: email
        session.values.currentUsuario = result
    }
}