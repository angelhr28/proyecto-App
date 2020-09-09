package com.example.proyectsad.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.proyectsad.R
import com.example.proyectsad.ui.slider.Slider
import com.example.proyectsad.ui.slider.SliderAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sliderAdapter by lazy { SliderAdapter() }
    private val sliderList = listOf(
        Slider("Tittle 1","asdasdasdasdasasdasdasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",R.drawable.image_slider_1),
        Slider("Tittle 2","asdasdasdasdasasdasdasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",R.drawable.image_slider_2),
        Slider("Tittle 3","asdasdasdasdasasdasdasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",R.drawable.image_slider_3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Slider
        sliderAdapter.setDataList(sliderList)
        slider_view_pager.adapter = sliderAdapter
        setUpIndicators()
        slider_view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
    }

    private fun setUpIndicators(){
        val indicators = arrayOfNulls<ImageView>(sliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)

        for(i in indicators.indices){
            indicators[i] = ImageView(this)
            indicators[i].apply {
                this?.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_inactive_slider))
                this?.layoutParams = layoutParams
            }
            container_indicators_slider.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index:Int){
        val childCount = container_indicators_slider.childCount
        for (i in 0 until childCount){
            val imageView = container_indicators_slider[i] as ImageView
            if(i == index) imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_active_slider))
            else  imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.indicator_inactive_slider))
        }
    }

}