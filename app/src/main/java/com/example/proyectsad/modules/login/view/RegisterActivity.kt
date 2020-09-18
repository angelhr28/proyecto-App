package com.example.proyectsad.modules.login.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.getColoredSpanned
import com.example.proyectsad.helper.aplication.setColorToNavigatioBar
import com.example.proyectsad.helper.aplication.setColorToStatusBar
import com.example.proyectsad.modules.login.mvp.RegisterMVP
import com.example.proyectsad.modules.login.presenter.RegisterPresenter
import com.example.proyectsad.root.ctx
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_register.*
import androidx.core.widget.addTextChangedListener as addTextChangedListener1

class RegisterActivity : AppCompatActivity(),RegisterMVP.View {

    private var cedtUsername      : TextInputLayout       ? = null
    private var cedtEmail         : TextInputLayout       ? = null
    private var cedtPassword      : TextInputLayout       ? = null
    private var edtUsername       : TextInputEditText     ? = null
    private var edtEmail          : TextInputEditText     ? = null
    private var edtPassword       : TextInputEditText     ? = null

    private var btnSignUpRegister : Button       ? = null
    private var lblDescLogin      : TextView     ? = null
    private var txtWatcher        : TextWatcher  ? = null
    private lateinit var presenter: RegisterMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)

        val signInQuestion  :String? = getString(R.string.lbl_register_sign_in_qst).getColoredSpanned(getString(R.string.color_black))
        val signInText      :String? = getString(R.string.lbl_register_sign_in).getColoredSpanned(getString(R.string.color_white))

        //Initializations
        btnSignUpRegister   = btn_sign_up_register
        cedtUsername        = cedt_username
        cedtEmail           = cedt_email
        cedtPassword        = cedt_password
        edtUsername         = edt_username
        edtEmail            = edt_email
        edtPassword         = edt_password
        lblDescLogin        = lbl_desc_login

        presenter           = RegisterPresenter(this)
        txtWatcher          = getTextWatcher()

        edtUsername?.addTextChangedListener(txtWatcher)
        edtEmail?.addTextChangedListener(txtWatcher)
        edtPassword?.addTextChangedListener(txtWatcher)

        btnSignUpRegister?.apply {
            isEnabled = false
            setBackgroundResource(R.drawable.btn_corner_dissable)
        }

        lblDescLogin?.apply {
            text = Html.fromHtml("$signInQuestion $signInText")
            setOnClickListener {
                onBackPressed()
            }
        }

    }

    override fun showError(msgError: String) {
        Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        //
    }

    override fun hideProgress() {
        //
    }

    override fun signUp() {

    }

    override fun navigationToSignIn() {
        val intent = Intent(ctx,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigationToIntro() {
        val intent = Intent(ctx,IntroduccionActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        this.finish();
        intent?.apply {
            overridePendingTransition(R.anim.right_in,R.anim.right_out)
        }
    }

    private fun getTextWatcher():TextWatcher{
        return object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val username = edtUsername?.text.toString().trim()
                val email    = edtEmail?.text.toString().trim()
                val password = edtPassword?.text.toString().trim()

                if(username.isEmpty() && email.isEmpty() && password.isEmpty()){
                    btnSignUpRegister?.apply {
                        isEnabled = false
                        setBackgroundResource(R.drawable.btn_corner_dissable)
                    }
                }else{
                    btnSignUpRegister?.apply {
                        isEnabled = true
                        setBackgroundResource(R.drawable.btn_corner)
                        setOnClickListener { validations(username, email, password) }
                    }
                }
            }
        }
    }

    private fun validations(username:String,email:String,password:String){
        if(presenter.checkEmptyInputs(username)){
            cedtUsername?.error = "Inserte su nombre completo"
            return
        }else cedtUsername?.error = null

        if(presenter.checkEmptyInputs(email)){
            cedtEmail?.error    = "Inserte un email"
            return
        }else cedtEmail?.error = null

        if(!presenter.checkValidEmail(email)){
            cedtEmail?.error = "Inserte un email valido"
            return
        }else cedtEmail?.error = null

        if(presenter.checkEmptyInputs(password)){
            cedtPassword?.error = "Inserte una contraseña"
            return
        }else cedtPassword?.error = null

        presenter.signUpUser(username, email, password)
    }

}