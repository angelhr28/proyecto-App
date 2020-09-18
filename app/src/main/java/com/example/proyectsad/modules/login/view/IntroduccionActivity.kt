package com.example.proyectsad.modules.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.*
import com.example.proyectsad.helper.viewTransform.FadePageTransfomer
import com.example.proyectsad.helper.viewTransform.MyPageAdapter
import com.example.proyectsad.modules.login.mvp.LoginMVP
import com.example.proyectsad.root.ctx
import kotlinx.android.synthetic.main.activity_introduccion.*

class IntroduccionActivity : AppCompatActivity() {

    private var btnSignUp   : AppCompatButton? = null
    private var btnSignIn   : AppCompatButton? = null
    private var lnIndSlider : LinearLayout?    = null
    private var vpSlider    : ViewPager?      = null

    private var listView    : MutableList<ImageView> = mutableListOf()
    private var mPageAdapter: MyPageAdapter? = null

    private val alphaHidden = 85
    private val marginItem  = 8
    private val marginMin   = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduccion)
        setColorToStatusBar(this)
        setColorToNavigatioBar(this)

        btnSignUp   = btn_sign_up
        btnSignIn   = btn_sign_in
        lnIndSlider = ln_ind_slider
        vpSlider    = vp_slider

        val fragments: List<Fragment> = getFragments()
        mPageAdapter = MyPageAdapter(supportFragmentManager, fragments)

        vpSlider?.setPageTransformer(false, FadePageTransfomer())
        vpSlider?.adapter = mPageAdapter

        addBottomDots(0)

        vpSlider?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                addBottomDots(position)
            }
        })

        btnSignUp?.setOnClickListener {
            val intent = Intent(ctx, RegisterActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
        btnSignIn?.setOnClickListener {
            val intent = Intent(ctx, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }

    private fun getFragments() : List<Fragment> {
        val fList = arrayListOf<Fragment>()

        fList.add(IntroFragment().newInstance(R.drawable.image_slider_1,
            getString(R.string.intro_primera),
            getString(R.string.intro_desc_primera)))
        fList.add(IntroFragment().newInstance(R.drawable.image_slider_2,
            getString(R.string.intro_segunda),
            getString(R.string.intro_desc_segunda)))
        fList.add(IntroFragment().newInstance(R.drawable.image_slider_3,
            getString(R.string.intro_tercera),
            getString(R.string.intro_desc_tercera)))
        return fList
    }

    private fun addBottomDots(currentPage: Int) {
        lnIndSlider?.removeAllViews()
        val drawaInactive = ContextCompat.getDrawable(ctx, R.drawable.indicator_inactive_slider)
        val drawaActive= ContextCompat.getDrawable(ctx, R.drawable.indicator_active_slider)
        drawaInactive?.alpha = alphaHidden
        mPageAdapter?.count?.let {
            for (i: Int in 0 until it) {
                listView.add(ImageView(this))
                listView[i].setImageDrawable(drawaInactive)
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(marginItem, marginMin, marginItem, marginMin)
                lnIndSlider?.addView(listView[i], params)
            }
            listView[currentPage].setImageDrawable(drawaActive)
        }
    }

}