package com.example.proyectsad.modules.login.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectsad.R
import com.example.proyectsad.modules.login.model.pojo.Slider

class SliderAdapter:RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private var sliderDataList = listOf<Slider>()

    fun setDataList(data:List<Slider>){
        sliderDataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container,parent,false)
        return SliderViewHolder(view)
    }

    override fun getItemCount(): Int = sliderDataList.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bindView(sliderDataList[position])
    }

    inner class SliderViewHolder(view: View):RecyclerView.ViewHolder(view){

        private val textTitle:TextView         = view.findViewById(R.id.txtSliderTitle)
        private val textDescription:TextView   = view.findViewById(R.id.txtSliderDescription)
        private val imageIcon:ImageView        = view.findViewById(R.id.imageSliderIcon)

        fun bindView(slider: Slider){
            textTitle.text       = slider.title
            textDescription.text = slider.description
            imageIcon.setImageResource(slider.image)
        }
    }
}