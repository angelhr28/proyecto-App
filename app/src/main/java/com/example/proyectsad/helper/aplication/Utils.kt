package com.example.proyectsad.helper.aplication

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.proyectsad.R
import com.example.proyectsad.root.ctx

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
fun setColorToNavigatioBar(
    activity: Activity, color: Int = ContextCompat.getColor(
        ctx,
        R.color.color_primary
    )
) {
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

private fun getColoredSpanned(text: String, color: String): String? {
    return "<font color=$color>$text</font>"
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
