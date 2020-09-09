package pe.softhy.smiledu.root

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.proyectsad.helper.aplication.MySharedPreferences
import com.example.proyectsad.root.AppDatabase
import com.facebook.stetho.Stetho

val preferences       : MySharedPreferences by lazy { MyApp.prefs!! }
lateinit var ctx      : Context

class MyApp: Application(){

    private val group1 = "Group-01"
    private val group2 = "Group-02"

    companion object {
        var prefs: MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Stetho.initializeWithDefaults(this)
        prefs = MySharedPreferences(applicationContext)
        ctx = this
        AppDatabase.getDatabase(this)
//        db  = DBOpenHelper.getInstance(this)!!

//        component = DaggerApplicationComponent.builder()
//                            .applicationModule(ApplicationModule(this))
//                            .searchSchoolModule(AsistenciaModule())
//                            .build()

        /*val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
        val built = builder.build()
        built.setIndicatorsEnabled(true)
        built.isLoggingEnabled = true
        Picasso.setSingletonInstance(built)*/
    }

}
