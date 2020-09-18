package com.example.proyectsad.modules.login.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.*
import com.example.proyectsad.helper.viewTransform.CircleTransform
import com.example.proyectsad.root.UnsafeOkHttpClient
import com.example.proyectsad.root.ctx
import com.google.firebase.FirebaseApp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var imgSplashScreem     : ImageView?   = null
    private var imgProgressBar      : ImageView?   = null
    private var handler             : Handler?     = null
    private var runnable            : Runnable?    = null
    private var animated            : AnimatedVectorDrawableCompat? = null
    private var progressBarStatus   : Int?         = 0
    private var dummy               : Int?         = 0
    private val SPLASH_DELAY        : Long   = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setColorToStatusBar(this)
        FirebaseApp.initializeApp(this)
        setColorToNavigatioBar(this, Color.WHITE)
        UnsafeOkHttpClient.initializeSSLContext(this)

        imgSplashScreem   = img_splash_screem
        imgProgressBar    = img_progress_bar

        Picasso.get()
            .load(R.drawable.img_recovery_password)
            .transform(CircleTransform())
            .centerCrop()
            .fit()
            .into(imgSplashScreem)

        animated          = AnimatedVectorDrawableCompat.create(this,  R.drawable.progress_bar)  // todos los contextos que usan THIS   cambialos por ctx pero "ojo solo si es un contexto"
        progressBarAnimated(animated)

        handler           = Handler()
        runnable          = getRunnable()
    }

    override fun onResume() {
        super.onResume()
        handler?.postDelayed(runnable!!,SPLASH_DELAY)  // @pendientes  quita todos los !! de la app no digas que puede ser distinto de null usa los lets
    }

    private fun getRunnable():Runnable?{
        return Runnable {
            Thread(Runnable {   // @Pendiente si te dice que esta demas colocarlo usas los landas
                while (progressBarStatus!! < 50) {
                    try {
                        dummy = dummy?.plus(1)
                        Thread.sleep(50)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressBarStatus = dummy
                }
                goToActivity()
            }).start()
        }
    }

    private fun goToActivity(){
//        @pendiente Al realizar un intent usar el formato comentado
//        val intent = Intent(ctx,RegisterActivity::class.java)
//        intent.apply{
//            startActivity(intent)
//        }

        startActivity(Intent(ctx,IntroduccionActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    private fun progressBarAnimated(animated: AnimatedVectorDrawableCompat?){
        animated?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                imgProgressBar?.post { animated.start() }
            }
        })
        val drawable = animated
//        drawable?.setColorFilter(ContextCompat.getColor(ctx,R.color.color_error) , PorterDuff.Mode.SRC_ATOP )
        imgProgressBar?.setImageDrawable(drawable)
        drawable?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (handler != null) handler!!.removeCallbacks(runnable!!)   //@pendiente Usa los lets y no lo valides por ifs
    }

}