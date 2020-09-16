package com.example.proyectsad.modules.login.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.*
import com.example.proyectsad.helper.aplication.Constants.SPLASH_DELAY
import com.example.proyectsad.root.ctx
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this, Color.WHITE)

        imgSplashScreem   = img_splash_screem
        imgProgressBar    = img_progress_bar

        Picasso.get()
            .load(R.drawable.img_recovery_password)
            .transform(CircleTransform())
            .centerCrop()
            .fit()
            .into(imgSplashScreem)

        animated          = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_anim_progress_bar)
        progressBarAnimated(animated)

        handler           = Handler()
        runnable          = getRunnable()
    }

    override fun onResume() {
        super.onResume()
        handler?.postDelayed(runnable!!,SPLASH_DELAY)
    }

    private fun getRunnable():Runnable?{
        return Runnable {
            Thread(Runnable {
                while (progressBarStatus!! < 100) {
                    // performing some dummy operation
                    try {
                        dummy = dummy?.plus(1)
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    // tracking progress
                    progressBarStatus = dummy
                }
                goToActivity()
            }).start()
        }
    }

    private fun goToActivity(){
        startActivity(Intent(ctx,IntroduccionActivity::class.java))
        finish()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    private fun progressBarAnimated(animated: AnimatedVectorDrawableCompat?){
        animated?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                imgProgressBar?.post { animated.start() }
            }
        })
        imgProgressBar?.setImageDrawable(animated)
        animated?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (handler != null) handler!!.removeCallbacks(runnable!!)
    }

}