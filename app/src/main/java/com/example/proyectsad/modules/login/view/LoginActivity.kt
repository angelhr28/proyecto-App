package com.example.proyectsad.modules.login.view

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.*
import com.example.proyectsad.root.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_recovery_password.view.*

class LoginActivity : AppCompatActivity() {

    private var lblDescRegister : TextView? = null
    private var lblRecupPass    : TextView? = null
    private var viewCortinaLogin: View?     = null

    private val whidthDialog    : Int = 336
    private val TAG = this::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)

        lblDescRegister  = lbl_desc_register
        lblRecupPass     = lbl_recup_pass
        viewCortinaLogin = view_cortina_login

        lblRecupPass?.setOnClickListener {
            setColorToNavigatioBar(this, ContextCompat.getColor(ctx,R.color.color_white_cortina))
            viewCortinaLogin?.visibility = View.VISIBLE
            dialogDefault(this, R.layout.dialog_recovery_password, whidthDialog){ view, dialog ->
                val imgRecoveryPass = view.img_recovery_pass
                val corner = calcularPxToDps(ctx, 8)


                Picasso.get()
                    .load(R.drawable.img_recovery_password)
                    .transform(CircleTransform())
                    .centerCrop()
                    .fit()
                    .into(imgRecoveryPass)

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
                val intent = Intent(ctx,RegisterActivity::class.java)
                intent.apply{
                    startActivity(intent)
                }
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        }



    }
}