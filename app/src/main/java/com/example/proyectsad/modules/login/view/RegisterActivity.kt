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
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.*
import com.example.proyectsad.modules.login.mvp.RegisterMVP
import com.example.proyectsad.modules.login.presenter.RegisterPresenter
import com.example.proyectsad.root.ctx
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_register.*
import androidx.core.widget.addTextChangedListener as addTextChangedListener1

class RegisterActivity : AppCompatActivity(),RegisterMVP.View {

    private var contParentRegister: ConstraintLayout      ? = null
    private var cedtUsername      : TextInputLayout       ? = null
    private var cedtEmail         : TextInputLayout       ? = null
    private var cedtPassword      : TextInputLayout       ? = null
    private var edtUsername       : TextInputEditText     ? = null
    private var edtEmail          : TextInputEditText     ? = null
    private var edtPassword       : TextInputEditText     ? = null
    private var lblDescLogin      : TextView              ? = null
    private var btnSignUpRegister : AppCompatButton       ? = null

    private var presenter         : RegisterMVP.Presenter ? = null

    private val TAG = this::class.java.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)

        //Initializations
        contParentRegister  = cont_parent_register
        cedtUsername        = cedt_username
        cedtEmail           = cedt_email
        cedtPassword        = cedt_password
        edtUsername         = edt_username
        edtEmail            = edt_email
        edtPassword         = edt_password
        lblDescLogin        = lbl_desc_login
        btnSignUpRegister   = btn_sign_up_register

        presenter           = RegisterPresenter(this)

        btnSignUpRegister?.apply {
            isEnabled = false
            setBackgroundResource(R.drawable.btn_corner_dissable)
        }

        //Validations
        textFieldsValidations()

        //onClickListener
        btnSignUpRegister?.apply {
            setOnClickListener { signUp() }
        }

        lblDescLogin?.apply {
            val signInQuestion  :String? = getString(R.string.lbl_register_sign_in_qst).getColoredSpanned(getString(R.string.color_black))
            val signInText      :String? = getString(R.string.lbl_register_sign_in).getColoredSpanned(getString(R.string.color_white))
            text = Html.fromHtml("$signInQuestion $signInText")
            setOnClickListener {
                //onBackPressed()
                navigationToSignIn()
            }
        }

    }

    override fun showError(msgError: String) {
        contParentRegister?.showSimpleSnackbar(msgError){}
    }

    override fun showProgress() {
        //doSomething
    }

    override fun hideProgress() {
        //doSomething
    }

    override fun showSnackBar(msg: String) {
        contParentRegister?.showSimpleSnackbar(msg){}
    }

    override fun signUpSucess() {

    }

    override fun signUpFailure(msgFailure: String) {

    }

    override fun navigationToSignIn() {
        val intent = Intent(ctx,LoginActivity::class.java)
        intent.apply {
            startActivity(this)
        }
        finish()
    }

    override fun navigationToIntro() {
        val intent = Intent(ctx,IntroduccionActivity::class.java)
        intent.apply{
            startActivity(this)
        }
        finish()
    }

    override fun onBackPressed() {
        this.finish();
        intent?.apply {
            overridePendingTransition(R.anim.right_in,R.anim.right_out)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    private fun textFieldsValidations() {

        val validate = afterTextChanged {
            val username = edtUsername?.text.toString().trim()
            val email = edtEmail?.text.toString().trim()
            val password = edtPassword?.text.toString().trim()

            val isEmailValid = emailValid(email)

            btnSignUpRegister?.apply {
                isEnabled = validateButton(username, email, password)
                if (isEnabled && isEmailValid) setBackgroundResource(R.drawable.btn_corner)
                else setBackgroundResource(R.drawable.btn_corner_dissable)
            }

        }
        edtUsername?.addTextChangedListener(validate)
        edtEmail?.addTextChangedListener(validate)
        edtPassword?.addTextChangedListener(validate)
    }

    override fun signUp() {
        val username = edtUsername?.text.toString().trim().removerTildes()
        val email = edtEmail?.text.toString().trim().removerTildes()
        val password = edtPassword?.text.toString().trim().removerTildes()
        presenter?.signUpStandard(username, email, password)
    }

    private fun validateButton(username:String,email:String,password:String):Boolean{
        if(isNullOrEmpty(username)) return false
        if(isNullOrEmpty(email)) return false
        if(isNullOrEmpty(password)) return false
        return true
    }

    private fun emailValid(email: String):Boolean{
        return when (isValidateEmail(email)) {
            false -> { cedtEmail?.error = "Ingrese un email valido"; false }
            else  -> { cedtEmail?.error = null ; true }
        }
    }

//    private fun passwordValid(password: String):Boolean{
//        return when{
//            password.length <=4 -> { cedtPassword?.error = "La contraseña debe tener minimo 4 caracteres" ; false }
//            password.length <=8 -> { cedtPassword?.error = "La contraseña debe tener minimo 8 caracteres" ; false }
//            else->{ cedtPassword?.error = null ; true}
//        }
//    }
}