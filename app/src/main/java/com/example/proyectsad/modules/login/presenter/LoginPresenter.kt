package com.example.proyectsad.modules.login.presenter

import com.example.proyectsad.R
import com.example.proyectsad.helper.saveCache.DataStandar
import com.example.proyectsad.helper.aplication.isConnected
import com.example.proyectsad.helper.aplication.isNullOrEmpty
import com.example.proyectsad.helper.saveCache.MasiquySession
import com.example.proyectsad.modules.login.model.LoginModel
import com.example.proyectsad.modules.login.model.pojo.DataUser
import com.example.proyectsad.modules.login.mvp.LoginMVP
import com.example.proyectsad.root.ctx
import com.example.proyectsad.root.preferences
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import retrofit2.HttpException

class LoginPresenter(val view: LoginMVP.View):LoginMVP.Presenter {

    private val TAG = LoginPresenter::class.java.name
    private val model = LoginModel()
    private val session   : MasiquySession = MasiquySession.getInstance()
    private val compositeDisposable = CompositeDisposable()

    override fun logInStandart(user: String, password: String) {
        when{
            isNullOrEmpty(user)     -> return view.logInError(ctx.getString(R.string.ingrese_usuario))
            isNullOrEmpty(password) -> return view.logInError(ctx.getString(R.string.ingrese_clave))
            !isConnected(ctx)       -> return view.logInError(ctx.getString(R.string.sin_conexion))
        }
        view.showProgress("Cargando...")

        val disposable = model.logInStandart(user, password).subscribe(
            { result ->
                setDataUserCache(result, user)
                view.logInSuccessful()
                view.hideProgress()
            },
            { error ->
                view.hideProgress()
                if (error is HttpException) {
                    val response = error.response()
                    try {
                        val jObjError = JSONObject(response.errorBody()?.string())
                        val result = Gson().fromJson(jObjError.toString(), DataStandar::class.java)
                        view.logInError(result.msj ?: "")
                    } catch (e: Exception) {
                        view.logInError("${ctx.getString(R.string.error_service)} -- ${e.message.toString()}")
                    }
                } else {
                    view.logInError("${ctx.getString(R.string.error_general)} -- ${error.message.toString()}")
                }
            }
        )
        compositeDisposable.add(disposable)
    }

    override fun logInRedes(user: String) {
        when{
            isNullOrEmpty(user)     -> return view.logInError(ctx.getString(R.string.ingrese_usuario))
            !isConnected(ctx)       -> return view.logInError(ctx.getString(R.string.sin_conexion))
        }
        view.showProgress("Cargando...")
        val disposable = model.logInRedes(user).subscribe(
            { result ->
                setDataUserCache(result, user)
                view.logInSuccessful()
                view.hideProgress()
            },
            { error ->
                view.hideProgress()
                if (error is HttpException) {
                    val response = error.response()
                    try {
                        val jObjError = JSONObject(response.errorBody()?.string())
                        val result = Gson().fromJson(jObjError.toString(), DataStandar::class.java)
                        view.logInError(result.msj ?: "")
                    } catch (e: Exception) {
                        view.logInError("${ctx.getString(R.string.error_service)} -- ${e.message.toString()}")
                    }
                } else {
                    view.logInError("${ctx.getString(R.string.error_general)} -- ${error.message.toString()}")
                }
            }
        )
        compositeDisposable.add(disposable)
    }

    override fun recoveryPassword(user: String) {
        when{
            isNullOrEmpty(user)     -> return view.logInError(ctx.getString(R.string.ingrese_usuario))
            !isConnected(ctx)       -> return view.logInError(ctx.getString(R.string.sin_conexion))
        }
        view.showProgress("Cargando...")
        val disposable = model.recoveryPassword(user).subscribe(
            { result ->
                view.hideProgress()
                view.showSnackbar(result.msj ?: "")
            },
            { error ->
                view.hideProgress()
                if (error is HttpException) {
                    val response = error.response()
                    try {
                        val jObjError = JSONObject(response.errorBody()?.string())
                        val result = Gson().fromJson(jObjError.toString(), DataStandar::class.java)
                        view.logInError(result.msj ?: "")
                    } catch (e: Exception) {
                        view.logInError("${ctx.getString(R.string.error_service)} -- ${e.message.toString()}")
                    }
                } else {
                    view.logInError("${ctx.getString(R.string.error_general)} -- ${error.message.toString()}")
                }
            }
        )
        compositeDisposable.add(disposable)
    }

    override fun ondestroy() {
        if (compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    fun setDataUserCache(result: DataUser, user: String){
        preferences.user      = result.usuario?.usuario ?: user
        preferences.token     = result.usuario?.token ?: ""
        preferences.fotoUser  = result.usuario?.fotoUsuario ?: ""
        preferences.idPersona = result.usuario?.idUsuario ?: 0
        preferences.nameCompletoUser  = result.usuario?.nombreAbvr ?: ""
        session.values.currentUsuario = result
    }

}