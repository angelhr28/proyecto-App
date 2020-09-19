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
            isNullOrEmpty(username)     -> return view.signUpFailure(ctx.getString(R.string.ingrese_nuevo_nombre))
            isNullOrEmpty(email)        -> return view.signUpFailure(ctx.getString(R.string.ingrese_nuevo_email))
            isNullOrEmpty(password)     -> return view.signUpFailure(ctx.getString(R.string.ingrese_nuevo_clave))
            !isConnected(ctx)           -> return view.signUpFailure(ctx.getString(R.string.sin_conexion))
        }
        view.showProgress()
        val disposable = model.signUpStandard(username, email, password).subscribe(
            {result->
                setUserDataCache(result, username, email)
                view.hideProgress()
                view.showSnackBar(result.msj ?: "")
            },
            {error->
                view.hideProgress()
                if (error is HttpException) {
                    val response = error.response()
                    try {
                        val jObjError = JSONObject(response.errorBody()?.string())
                        val result = Gson().fromJson(jObjError.toString(), DataStandar::class.java)
                        view.signUpFailure(result.msj ?: "")
                    } catch (e: Exception) {
                        view.signUpFailure("${ctx.getString(R.string.error_service)} -- ${e.message.toString()}")
                    }
                } else {
                    view.signUpFailure("${ctx.getString(R.string.error_general)} -- ${error.message.toString()}")
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
        preferences.token     = result.usuario?.token ?: ""
        preferences.idPersona = result.usuario?.idUsuario ?: 0
        preferences.user      = result.usuario?.usuario ?: email
        preferences.nameCompletoUser  = result.usuario?.nombreAbvr ?: username
        session.values.currentUsuario = result
    }
}