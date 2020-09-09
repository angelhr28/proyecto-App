package com.example.proyectsad.modules.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.proyectsad.R
import com.example.proyectsad.helper.aplication.FadePageTransfomer
import com.example.proyectsad.helper.aplication.MyPageAdapter
import com.example.proyectsad.modules.login.model.pojo.Slider
import com.example.proyectsad.modules.login.view.adapter.SliderAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var btnSignUp   : AppCompatButton? = null
    private var btnSignIn   : AppCompatButton? = null
    private var lnIndSlider : LinearLayout?    = null
    private var vpSlider    : ViewPager?      = null

    private var sliderAdapter :SliderAdapter? = null
    private lateinit var mPageAdapter: MyPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSignUp   = btn_sign_up
        btnSignIn   = btn_sign_in
        lnIndSlider = ln_ind_slider
        vpSlider    = vp_slider

//        val fragments: List<Fragment> = getFragments()
//        mPageAdapter = MyPageAdapter(supportFragmentManager, fragments)
//
//        mViewpager.setPageTransformer(false, FadePageTransfomer())
//        mViewpager.adapter = mPageAdapter
//
//
//        sliderAdapter = SliderAdapter()
//        sliderAdapter?.apply {
//            setDataList(sliderList)
//            vpSlider?.adapter = sliderAdapter
//        }
//
//        setUpIndicators()
//        vpSlider?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                setCurrentIndicator(position)
//            }
//        })
    }

//    private fun getFragments() : List<Fragment> {
//        val fList = arrayListOf<Fragment>()
//
//        fList.add(IntroFragment().newInstance(R.drawable.intro_primera,
//            getString(R.string.intro_primera),
//            getString(R.string.intro_desc_primera)))
//        fList.add(IntroFragment().newInstance(R.drawable.intro_segunda,
//            getString(R.string.intro_segunda),
//            getString(R.string.intro_desc_segunda)))
//        fList.add(IntroFragment().newInstance(R.drawable.intro_tercera,
//            getString(R.string.intro_tercera),
//            getString(R.string.intro_desc_tercera)))
//        fList.add(IntroFragment().newInstance(R.drawable.intro_cuarta,
//            getString(R.string.intro_cuarta),
//            getString(R.string.intro_desc_cuarta)))
//        fList.add(IntroFragment().newInstance(R.drawable.intro_quinta,
//            getString(R.string.intro_quinta),
//            getString(R.string.intro_desc_quinta)))
//        return fList
//    }


    private fun setUpIndicators(){
        val indicators = sliderAdapter?.itemCount?.let { arrayOfNulls<ImageView>(it) }
        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)

        for(i in indicators?.indices!!){
            indicators.set(i, ImageView(this))
            indicators.get(i).apply {
                this?.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_inactive_slider))
                this?.layoutParams = layoutParams
            }
            lnIndSlider?.addView(indicators?.get(i))
        }
    }

    private fun setCurrentIndicator(index:Int){
        val childCount = lnIndSlider?.childCount
        for (i in 0 until childCount!!){
            val imageView = lnIndSlider?.get(i) as ImageView
            if(i == index) imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_active_slider))
            else  imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_inactive_slider))
        }
    }


    private val sliderList = listOf(
        Slider("Tittle 1","asdasdasdasdasasdasdasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",R.drawable.image_slider_1),
        Slider("Tittle 2","asdasdasdasdasasdasdasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",R.drawable.image_slider_2),
        Slider("Tittle 3","asdasdasdasdasasdasdasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",R.drawable.image_slider_3)
    )

}