package com.amjadalwareh.cake

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Insets
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.SpannableString
import android.text.style.CharacterStyle
import android.text.style.StyleSpan
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowInsets
import android.view.WindowMetrics
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.amjadalwareh.cake.ContextKt.manager
import com.amjadalwareh.cake.ViewKt.italic
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.security.MessageDigest
import java.util.regex.Pattern

object Utils {

    const val HEIGHT = 0
    const val WIDTH = 1

    enum class HashType { MD5, SHA1, SHA256 }

    fun String.hashWithAlgorithm(type: HashType): String =
        MessageDigest.getInstance(type.name)
            .digest(this.toByteArray(Charsets.UTF_8))
            .joinToString(separator = "") { String.format("%02x", it) }


    fun calculateScreen(activity: Activity?, type: Int): Int =
        if (activity == null) -1 else
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics: WindowMetrics = activity.windowManager.currentWindowMetrics
                val insets: Insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
                if (type == HEIGHT) windowMetrics.bounds.height() - insets.top - insets.bottom
                else windowMetrics.bounds.width() - insets.left - insets.right
            } else {
                val displayMetrics = DisplayMetrics()
                activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
                if (type == HEIGHT) displayMetrics.heightPixels else displayMetrics.widthPixels
            }

    fun changeVisibility(view: View?, visibility: Int) {
        view?.visibility = visibility
    }

    fun match(patterns: Pattern, target: String): Boolean = patterns.matcher(target).matches()

    fun round(value: Double): Double = BigDecimal(value).setScale(3, BigDecimal.ROUND_HALF_UP).toDouble()

    fun getColor(context: Context, @ColorRes id: Int): Int = ContextCompat.getColor(context, id)

    fun getDrawable(context: Context, @DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(context, id)

    fun toast(context: Context, string: String, isShort: Boolean = true) {
        Toast.makeText(context, string, if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
    }

    fun snackbar(view: View, text: String, shortDuration: Boolean, button: String, funct: () -> Unit) {
        Snackbar.make(view, text, if (shortDuration) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG)
            .setAction(button) { funct() }
            .show()
    }

    fun snackbar(view: View, text: String, shortDuration: Boolean) {
        Snackbar.make(view, text, if (shortDuration) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG).show()
    }

    fun <T : CharacterStyle> applySpan(textView: TextView, span: T) {
        val text = SpannableString(textView.text.toString())
        text.setSpan(StyleSpan(Typeface.ITALIC), 0, text.length, 0)
        textView.text = text
    }

    fun hasSystemFeature(context: Context, featureName: String): Boolean = context.manager<PackageManager>().hasSystemFeature(featureName)
}