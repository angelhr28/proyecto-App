package com.example.proyectsad.modules.principal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectsad.R
import com.example.proyectsad.modules.login.mvp.PrincipalMVP
import com.example.proyectsad.modules.login.presenter.PrincipalPresenter

class PrincipalActivity : AppCompatActivity(), PrincipalMVP.View{

    private var presenter: PrincipalMVP.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        presenter = PrincipalPresenter(this)

    }

    override fun onBackPressed() {
        this.finish();
        intent?.apply {
            overridePendingTransition(R.anim.right_in,R.anim.right_out)
        }
    }
}