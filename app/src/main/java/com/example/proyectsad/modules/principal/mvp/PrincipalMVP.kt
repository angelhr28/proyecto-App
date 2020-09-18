package com.example.proyectsad.modules.login.mvp

import com.example.proyectsad.helper.saveCache.DataStandar
import com.example.proyectsad.modules.login.model.pojo.DataUser
import io.reactivex.Observable

interface PrincipalMVP{
    interface View{

    }
    interface Presenter{

        fun ondestroy()
    }
    interface Model{

    }
}