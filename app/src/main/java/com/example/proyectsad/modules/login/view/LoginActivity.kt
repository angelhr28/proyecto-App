package com.example.proyectsad.modules.login.view

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.dialogDefault
import com.example.proyectsad.helper.aplication.getColoredSpanned
import com.example.proyectsad.helper.aplication.setColorToNavigatioBar
import com.example.proyectsad.helper.aplication.setColorToStatusBar
import com.example.proyectsad.root.ctx
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_recovery_password.view.*

class LoginActivity : AppCompatActivity() {

    private var lblDescRegister : TextView? = null
    private var lblRecupPass    : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)

        lblDescRegister = lbl_desc_register
        lblRecupPass    = lbl_recup_pass


        /*lblRecupPass?.setOnClickListener {
            dialogDefault(this, 120,240, R.layout.dialog_recovery_password){ view, dialog ->
                val lblEliminar = view.lbl_opcion_msj_eliminar
                val lblCancelar = view.lbl_opcion_msj_cancelar
                lblEliminar.setOnClickListener {

                    dialog.dismiss()
                }
                lblCancelar.setOnClickListener { dialog.dismiss() }
            }
        }*/

        lblDescRegister?.apply {
            val signUpText    : String? = getString(R.string.registrate).getColoredSpanned(getString(R.string.color_white))
            val signInQuestion: String? = getString(R.string.no_tienes_u).getColoredSpanned(getString(R.string.color_black))
            text = Html.fromHtml("$signInQuestion $signUpText")
            setOnClickListener{
                val intent = Intent(ctx,RegisterActivity::class.java)
                intent.apply{
                    startActivity(intent)
                }
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        }



    }
}