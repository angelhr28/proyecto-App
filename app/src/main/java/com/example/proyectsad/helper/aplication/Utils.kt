package com.example.proyectsad.helper.aplication

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.text.*
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.proyectsad.R
import com.example.proyectsad.root.UnsafeOkHttpClient
import com.example.proyectsad.root.ctx
import com.example.proyectsad.root.preferences
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.layoutInflater
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// AQUI DEJAREMOS TODAS LAS FUNCIONES QUE REUTILIZAREMOS

fun setColorToStatusBar(activity: Activity, color: Int = Color.WHITE) {
    val window = activity.window
    val hsv = FloatArray(3)
    var darkColor: Int = color

    Color.colorToHSV(darkColor, hsv)
    hsv[2] *= 0.8f // value component
    darkColor = Color.HSVToColor(hsv)

    if (Build.VERSION.SDK_INT >= 21) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = darkColor //Define color
    }
}
fun setColorToNavigatioBar( activity: Activity, color: Int = ContextCompat.getColor(ctx,R.color.color_primary)) {
    val window = activity.window
    val hsv = FloatArray(3)
    var darkColor: Int = color

    Color.colorToHSV(darkColor, hsv)
    hsv[2] *= 0.8f // value component
    darkColor = Color.HSVToColor(hsv)

    if (Build.VERSION.SDK_INT >= 21) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.navigationBarColor = darkColor //Define color
    }
}

fun String.getColoredSpanned(color: String): String? {
    Log.e("TAG", "getColoredSpanned: $color" )
    return "<font color=$color>$this</font>"
}

fun String?.fromHtml() : Spanned? {
    val html =  this
        ?.replace("<strong>", "<b>")
        ?.replace("</strong>", "</b>")
        ?.replace("<ul>", "")
        ?.replace("</ul>", "")
        ?.replace("<li>", "<p>•&nbsp;&nbsp;&nbsp;")
        ?.replace(" ","&nbsp;")
        ?.replace("</li>", "</p>")

    return when {
        html == null -> SpannableString("")
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        else -> Html.fromHtml(html, null, MyTagHandler())
    }
}

fun calcularPxToDps(context: Context, pixels: Int): Int {
    val scale = context.resources.displayMetrics.density
    return (pixels*scale + 0.5f).toInt()
}

fun dialogDefault(ctx: Context, layout : Int, width:Int? = null, height:Int? = null, function: (View, AlertDialog) -> Unit ) {

    val ancho = width?.let { calcularPxToDps(ctx, it) } ?: WindowManager.LayoutParams.WRAP_CONTENT
    val alto  = height?.let { calcularPxToDps(ctx, it) } ?: WindowManager.LayoutParams.WRAP_CONTENT
    val dialog = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) AlertDialog.Builder(
        ctx,
        R.style.CustomDialogBackground
    ) else AlertDialog.Builder(ctx)

    val view = ctx.layoutInflater.inflate(layout, null)
    val customDialog = dialog.create()
    customDialog.apply {
        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        setView(view)
        function(view,this)
        show()
        window?.setLayout(ancho, alto)
    }
}

fun isNullOrEmpty(text: Any): Boolean {
    return when (text) {
        is String -> text.trim().isEmpty()
        is EditText -> text.text.trim().isEmpty()
        else -> false
    }
}

fun isConnected(context: Context): Boolean {
    val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork:  NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun String.removerTildes(): String {
    return this.replace("Á", "A")
        .replace("É", "E")
        .replace("Í", "I")
        .replace("Ó", "O")
        .replace("Ú", "U")
        .replace("á", "a")
        .replace("é", "e")
        .replace("í", "i")
        .replace("ó", "o")
        .replace("ú", "u");
}

fun getTokenFirebase() : String? {
    var deviceToken: String? = "1"
    if (isNullOrEmpty(preferences.deviceToken)) {
        try {
            deviceToken = FirebaseInstanceId.getInstance().instanceId.result?.token
        } catch (e: Exception) {
            Log.e("Error-GetTokenFirebase", e.message)
        }
    } else {
        deviceToken = preferences.deviceToken
    }
    return deviceToken
}

fun View.showSimpleSnackbar(message: String, duration: Int = Snackbar.LENGTH_LONG, margin: Int = 20, function: (Snackbar) -> Unit){
    val snackbar = Snackbar.make(this, message, duration)
    val view = snackbar.view
    val textview = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textview.setTextColor(ContextCompat.getColor(ctx, R.color.color_white))
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        view.elevation = 120.0f
    }
    val fondo = ContextCompat.getDrawable(ctx, R.drawable.backgound_snackbar)
    view.backgroundDrawable = (fondo)
    val margen : ViewGroup.MarginLayoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    margen.setMargins(20,0,20, calcularPxToDps(ctx,margin))
    function(snackbar)
    snackbar.show()
}

fun getConexionRetrofit(url_base: String = Constants.ROOT_URL): Retrofit {
    val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
    return Retrofit.Builder()
        .baseUrl(url_base)
        .client(okHttpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun Context.getVersionName(): String {
    var versionName = ""
    try {
        versionName = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0).versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return versionName
}

fun Any.toJson(): String = Gson().toJson(this)

fun metadataUser() : String{
    return hashMapOf(
        "platform" to Constants.PLATFORM,
        "token"    to getTokenFirebase(),
        "version"  to Build.VERSION.RELEASE,
        "modelo"   to Build.MODEL,
        "sdk"      to Build.VERSION.SDK_INT,
        "marca"    to Build.BRAND,
        "app_version" to ctx.getVersionName()).toJson()
}

fun isValidateEmail(email : String) : Boolean {
    val pattern= Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun afterTextChanged(function: (s: Editable) -> Unit): TextWatcher {
    return object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }
        override fun afterTextChanged(s: Editable) {
            function(s)
        }
    }
}