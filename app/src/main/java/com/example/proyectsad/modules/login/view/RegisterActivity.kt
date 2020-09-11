package com.example.proyectsad.modules.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.getColoredSpanned
import com.example.proyectsad.helper.aplication.setColorToNavigatioBar
import com.example.proyectsad.helper.aplication.setColorToStatusBar
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private var lblDescLogin:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)

        val signInQuestion  :String? = getString(R.string.lbl_register_sign_in_qst).getColoredSpanned(getString(R.string.color_black))
        val signInText      :String? = getString(R.string.lbl_register_sign_in).getColoredSpanned(getString(R.string.color_white))

        lblDescLogin = lbl_desc_login
        lblDescLogin?.text = Html.fromHtml("$signInQuestion $signInText")

    }



}