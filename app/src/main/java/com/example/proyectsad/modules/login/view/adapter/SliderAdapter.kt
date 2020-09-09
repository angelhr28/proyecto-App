package com.example.proyectsad.modules.login.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectsad.R
import com.example.proyectsad.modules.login.model.pojo.Slider
import kotlinx.android.synthetic.main.slider_item_container.view.*

class SliderAdapter:RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private var sliderDataList = listOf<Slider>()

    fun setDataList(data:List<Slider>){
        sliderDataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container,parent,false))
    }

    override fun getItemCount(): Int = sliderDataList.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bindView(sliderDataList[position])
    }

    class SliderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        private var lblTitleSlider:TextView?  = null
        private var lblDescSlider :TextView?  = null
        private var imgSlider     :ImageView? = null

        fun bindView(slider: Slider){
            itemView.apply {
                lblTitleSlider = lbl_title_slider
                lblDescSlider  = lbl_desc_slider
                imgSlider      = img_slider

                lblTitleSlider?.text       = slider.title
                lblDescSlider?.text = slider.description
                slider.image?.let { imgSlider?.setImageResource(it) }
            }
        }
    }
}