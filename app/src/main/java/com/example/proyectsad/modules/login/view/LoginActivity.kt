package com.example.proyectsad.modules.login.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.*
import com.example.proyectsad.helper.viewTransform.CircleTransform
import com.example.proyectsad.modules.login.mvp.LoginMVP
import com.example.proyectsad.modules.login.mvp.PrincipalMVP
import com.example.proyectsad.modules.login.presenter.LoginPresenter
import com.example.proyectsad.modules.principal.view.PrincipalActivity
import com.example.proyectsad.root.ctx
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_recovery_password.*
import kotlinx.android.synthetic.main.dialog_recovery_password.view.*
import java.util.*

class LoginActivity : AppCompatActivity(), LoginMVP.View {

    private var contParentLogin : ConstraintLayout?  = null
    private var lblDescRegister : TextView?          = null
    private var lblRecupPass    : TextView?          = null
    private var viewCortinaLogin: View?              = null
    private var pgLogin         : ProgressBar?       = null
    private var lblLoadLogin    : TextView?          = null
    private var btnLogin        : AppCompatButton?   = null
    private var edtUsuario      : TextInputEditText? = null
    private var edtPassword     : TextInputEditText? = null
    private var cedtPassword    : TextInputLayout? = null
    private var cedtUsuario     : TextInputLayout? = null

    private val whidthDialog : Int = 336
    private var presenter    : LoginMVP.Presenter? = null
    private val TAG = this::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)

        contParentLogin  = cont_parent_login
        lblDescRegister  = lbl_desc_register
        lblRecupPass     = lbl_recup_pass
        viewCortinaLogin = view_cortina_login
        pgLogin          = pg_login
        lblLoadLogin     = lbl_load_login
        btnLogin         = btn_login
        edtUsuario       = edt_usuario
        edtPassword      = edt_password
        cedtPassword     = cedt_password
        cedtPassword     = cedt_password
        presenter        = LoginPresenter(this)

        getButtonOption()
        getValidateTextChanged()
    }


    private fun getValidateTextChanged(){

        val validate= afterTextChanged {
            val user     = edtUsuario?.text.toString().trim()
            val password = edtPassword?.text.toString().trim()

            cedtPassword?.error = when{
                password.length <= 8 -> "La contraseña debe tener minimo 8 caracteres"
                else -> null
            }

            btnLogin?.apply {
                isEnabled = !isValidateEmail(user)
                         && !isNullOrEmpty(user)
                         && !isNullOrEmpty(password)
                         && password.length > 8
            }
        }
        edtUsuario?.addTextChangedListener(validate)
        edtPassword?.addTextChangedListener(validate)

    }

    private fun getButtonOption() {
        lblRecupPass?.setOnClickListener {
//            setColorToNavigatioBar(this, ContextCompat.getColor(ctx,R.color.color_white_cortina))
            viewCortinaLogin?.visibility = View.VISIBLE
            dialogDefault(this, R.layout.dialog_recovery_password, whidthDialog) { view, dialog ->
                val imgRecoveryPass    = view.img_recovery_pass
                val btnRecovery = view.btn_recovery
                val edtRecovery  = view.edt_recovery
                val cedtRecovery  = view.cedt_recovery

                Picasso.get()
                    .load(R.drawable.img_recovery_password)
                    .transform(CircleTransform())
                    .centerCrop()
                    .fit()
                    .into(imgRecoveryPass)

                btnRecovery.setOnClickListener {
                    presenter?.recoveryPassword(edtRecovery.text?.trim().toString().removerTildes())
                    dialog.dismiss()
                }

                val validate = afterTextChanged {
                    val text = it.toString().trim()

                    cedtRecovery?.error = when{
                        isValidateEmail(text) && text.length > 4 -> "El correo no es valido"
                        else -> null
                    }

                    btnRecovery?.apply {
                        isEnabled = !isValidateEmail(text) && !isNullOrEmpty(text)
                    }
                }
                edtRecovery.addTextChangedListener(validate)

                dialog.setOnDismissListener {
                    setColorToNavigatioBar(this)
                    viewCortinaLogin?.visibility = View.GONE
                }
            }
        }

        lblDescRegister?.apply {
            val signUpText    : String? = getString(R.string.registrate).getColoredSpanned(getString(R.string.color_white))
            val signInQuestion: String? = getString(R.string.no_tienes_u).getColoredSpanned(getString(R.string.color_black))
            text = Html.fromHtml("$signInQuestion $signUpText")
            setOnClickListener{
                val intent = Intent(ctx, RegisterActivity::class.java)
                intent.apply{
                    startActivity(intent)
                }
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        }

        btnLogin?.setOnClickListener {
            val user = edtUsuario?.text?.trim().toString().removerTildes()
            val pass = edtPassword?.text?.trim().toString().removerTildes()
            presenter?.logInStandart(user, pass)
        }
    }

    override fun logInSuccessful() {
        val intent = Intent(ctx, PrincipalActivity::class.java)
        intent.apply{
            startActivity(intent)
        }
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    override fun logInError(msj: String) {
        contParentLogin?.showSimpleSnackbar(msj){}
    }

    override fun showProgress(msj: String) {
        lblLoadLogin?.text = msj

        pgLogin?.visibility          = View.VISIBLE
        lblLoadLogin?.visibility     = View.VISIBLE
        viewCortinaLogin?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pgLogin?.visibility          = View.GONE
        lblLoadLogin?.visibility     = View.GONE
        viewCortinaLogin?.visibility = View.GONE
    }

    override fun showSnackbar(msj: String) {
        contParentLogin?.showSimpleSnackbar(msj){}
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.ondestroy()
    }
}