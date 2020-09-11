package com.example.proyectsad.modules.login.view

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.fromHtml
import com.example.proyectsad.helper.aplication.getColoredSpanned
import com.example.proyectsad.helper.aplication.setColorToNavigatioBar
import com.example.proyectsad.helper.aplication.setColorToStatusBar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var lblDescRegister : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)
        val registrate: String? = getString(R.string.registrate).getColoredSpanned(getString(R.string.color_white))
        val pregRegist: String? = getString(R.string.no_tienes_u).getColoredSpanned(getString(R.string.color_black))

        lblDescRegister = lbl_desc_register

        lblDescRegister?.text = Html.fromHtml("$pregRegist $registrate")


    }
}