package com.example.proyectsad.modules.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.proyectsad.R
import kotlinx.android.synthetic.main.slider_item_container.view.*

class IntroFragment : Fragment() {

    private val EXTRA_IMG   = "EXTRA_ING_INTRO_FRAGMENT"
    private val EXTRA_TITLE = "EXTRA_TITLE_INTRO_FRAGMENT"
    private val EXTRA_DESC  = "EXTRA_DESC_INTRO_FRAGMENT"

    private var lblTitleSlider: TextView?  = null
    private var lblDescSlider : TextView?  = null
    private var imgSlider     : ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_intro, container, false)

        lblTitleSlider = rootView.lbl_title_slider
        lblDescSlider  = rootView.lbl_desc_slider
        imgSlider      = rootView.img_slider

        arguments?.let {
            lblTitleSlider?.text = it.getString(EXTRA_TITLE)
            lblDescSlider?.text  = it.getString(EXTRA_DESC)
            imgSlider?.setImageResource(it.getInt(EXTRA_IMG))
        }

        return rootView
    }


    fun newInstance(img: Int, title: String, desc: String) =
        IntroFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_IMG     , img)
                putString(EXTRA_TITLE, title)
                putString(EXTRA_DESC , desc)
            }
        }

}