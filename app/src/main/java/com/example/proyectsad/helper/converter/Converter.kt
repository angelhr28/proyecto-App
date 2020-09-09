package com.example.proyectsad.helper.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

//    @TypeConverter
//    fun fromStringCompetencia(value: String): ArrayList<Items?>? {
//        val listType = object : TypeToken<ArrayList<Items?>?>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayListCompetencia(list: ArrayList<Items?>?): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }

}