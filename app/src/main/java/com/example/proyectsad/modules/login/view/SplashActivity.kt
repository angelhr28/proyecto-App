package com.example.proyectsad.modules.login.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.Constants.SPLASH_DELAY
import com.example.proyectsad.helper.aplication.setColorToNavigatioBar
import com.example.proyectsad.helper.aplication.setColorToStatusBar
import com.example.proyectsad.root.ctx
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var splashProgressBar   : ProgressBar? = null
    private var handler             : Handler?     = null
    private var runnable            : Runnable?    = null

    private var progressBarStatus   : Int?      = 0
    private var dummy               : Int?      = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this, Color.WHITE)

        splashProgressBar = splash_progress_bar
        handler           = Handler()
        runnable          = getRunnable()
    }

    override fun onResume() {
        super.onResume()
        handler?.postDelayed(runnable!!,SPLASH_DELAY)
    }

    private fun getRunnable():Runnable{
        return Runnable {
            Thread(Runnable {
                while (progressBarStatus!! < 100) {
                    try {
                        dummy = dummy?.plus(1)
                        Thread.sleep(10)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressBarStatus = dummy
                    splashProgressBar?.progress = progressBarStatus!!
                }
                goToActivity()
            }).start()
        }
    }

    private fun goToActivity(){
        startActivity(Intent(ctx,IntroduccionActivity::class.java))
    }


}