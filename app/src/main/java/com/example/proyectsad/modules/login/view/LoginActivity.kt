package com.example.proyectsad.modules.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.setColorToNavigatioBar
import com.example.proyectsad.helper.aplication.setColorToStatusBar

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)

    }
}