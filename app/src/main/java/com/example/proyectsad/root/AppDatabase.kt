package com.example.proyectsad.root

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.proyectsad.helper.converter.Converter

//
//@Database(entities = [
////    News::class
//], version = 1 )
//@TypeConverters(Converter::class)
//abstract class AppDatabase: RoomDatabase() {
//
////    abstract fun noticiaDao()        : NoticiaDao
//
//    companion object {
//        private var INSTANCE: AppDatabase? = null
//        fun getDatabase(context: Context): AppDatabase? {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "proyectSad.db")
//                        .allowMainThreadQueries()
//                        .fallbackToDestructiveMigration()
//                        .build()
//                }
//            }
//            return INSTANCE
//        }
//    }
//    fun destroyInstance() { INSTANCE = null }
//}
