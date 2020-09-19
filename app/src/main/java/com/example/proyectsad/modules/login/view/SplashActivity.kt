package com.example.proyectsad.modules.login.view

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.core.content.ContextCompat
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

        animated          = AnimatedVectorDrawableCompat.create(ctx,  R.drawable.progress_bar)
        progressBarAnimated(animated)

        handler           = Handler()
        runnable          = getRunnable()
    }

    override fun onResume() {
        super.onResume()
        handler?.let {
            it.postDelayed(runnable,SPLASH_DELAY)
        }
    }

    private fun getRunnable():Runnable?{
        return Runnable {
            Thread {
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
            }.start()
        }
    }

    private fun goToActivity(){
        val intent = Intent(ctx,IntroduccionActivity::class.java)
        intent.apply {
            startActivity(intent)
        }
        finish()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    private fun progressBarAnimated(animated: AnimatedVectorDrawableCompat?){
        animated?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                imgProgressBar?.post { animated.start() }
            }
        })
        val drawable = animated
        drawable?.setColorFilter(ContextCompat.getColor(ctx,R.color.color_primary) , PorterDuff.Mode.SRC_ATOP )
        imgProgressBar?.setImageDrawable(drawable)
        drawable?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.let {
            it.removeCallbacks(runnable)
        }
    }

}