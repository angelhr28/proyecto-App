package com.example.proyectsad.helper.aplication

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.proyectsad.R
import com.example.proyectsad.root.ctx
import org.jetbrains.anko.layoutInflater

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



