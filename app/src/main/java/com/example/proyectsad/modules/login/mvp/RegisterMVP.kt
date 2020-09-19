package com.example.proyectsad.modules.login.mvp

import com.example.proyectsad.modules.login.model.pojo.DataUser
import io.reactivex.Observable

interface RegisterMVP {

    interface View{
        fun showError(msgError: String) // @PENDIENTE PORQUE TIENES UN SHOW ERROR Y UN  signUpFailure  DECIDIR CUAL DE LOS DOS SE QUEDAD Y BORRAR EL OTRO
        fun showProgress()
        fun hideProgress()
        fun showSnackBar(msg: String)
        fun signUp()  // @PENDIENTE  PARA QUE MRD SIERVE ESTO ?  BORRAR
        fun signUpSucess()//@PENDIENTE LA PTMRE NO CREES METODOS INUTILES SI NO SON USADOS NO LOS CREEES
                          // AHORA ESAS MRDS DE ABAJO DE NAVEGACION PUEDE ESTAR DENTRO DE AQUI NO CREE ??
        fun signUpFailure(msgFailure: String)
        fun navigationToSignIn()  // @PENDIENTE LA PTMRE QUE PARTE DE SOLO SE COLOCA ACCIONES DE RESULTADO ????
                                  //   ESTA ACCION RESULTADO DE METODO DEL PRESENTE ES ???
        fun navigationToIntro() //METER DENTRO DEL signUpSucess
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