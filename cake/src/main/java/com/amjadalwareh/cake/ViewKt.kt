package com.amjadalwareh.cake

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amjadalwareh.cake.ContextKt.animation
import com.amjadalwareh.cake.Utils.HEIGHT
import com.amjadalwareh.cake.Utils.WIDTH
import com.amjadalwareh.cake.Utils.applySpan
import com.amjadalwareh.cake.Utils.calculateScreen
import com.amjadalwareh.cake.Utils.changeVisibility
import com.amjadalwareh.cake.Utils.snackbar
import com.amjadalwareh.cake.ViewKt.fadeIn
import com.amjadalwareh.cake.ViewKt.fadeOut


object ViewKt {

    /**
     * Change the view's visibility [View.VISIBLE]
     */
    fun View?.visible() {
        changeVisibility(this, View.VISIBLE)
    }

    /**
     * Check if view's visibility is [View.VISIBLE]
     */
    fun View?.isVisible(): Boolean = this?.visibility == View.VISIBLE

    /**
     * Change the view's visibility [View.INVISIBLE]
     */
    fun View?.invisible() {
        changeVisibility(this, View.INVISIBLE)
    }

    /**
     * Check if view's visibility is [View.INVISIBLE]
     */
    fun View?.isInvisible(): Boolean = this?.visibility == View.INVISIBLE

    /**
     * Change the view's visibility [View.GONE]
     */
    fun View?.gone() {
        changeVisibility(this, View.GONE)
    }

    /**
     * Check if view's visibility is [View.GONE]
     */
    fun View?.isGone(): Boolean = this?.visibility == View.GONE

    /**
     * Get screen width in px
     */
    fun Activity?.screenWidth(): Int = calculateScreen(this, WIDTH)

    /**
     * Get screen height in px
     */
    fun Activity?.screenHeight(): Int = calculateScreen(this, HEIGHT)

    /**
     * Check if recyclerview adapter contains data
     */
    fun RecyclerView.isEmpty(): Boolean = this.adapter?.itemCount == 0

    /**
     * Resize a view
     */
    fun View.resize(width: Int, height: Int) {
        this.layoutParams.height = height
        this.layoutParams.width = width
    }

    /**
     *
     */
    fun View.fadeIn() {
        this.startAnimation(context.animation(R.anim.fade_in))
    }

    /**
     *
     */
    fun View.fadeOut() {
        this.startAnimation(context.animation(R.anim.push_out_bottm))
    }

    /**
     *
     */
    fun View.glow() {
        this.startAnimation(context.animation(R.anim.glow))
    }

    /**
     * Show [com.google.android.material.snackbar.Snackbar]
     */
    fun View.showSnackbar(text: String, shortDuration: Boolean = true) {
        snackbar(this, text, shortDuration)
    }

    /**
     * Show [com.google.android.material.snackbar.Snackbar]
     */
    fun View.showSnackbar(@StringRes text: Int, shortDuration: Boolean = true) {
        snackbar(this, this.context.getString(text), shortDuration)
    }

    /**
     * Show [com.google.android.material.snackbar.Snackbar] and a function button
     */
    fun View.showSnackbar(
        text: String,
        shortDuration: Boolean = true,
        button: String,
        func: () -> Unit
    ) {
        snackbar(this, text, shortDuration, button, func)
    }

    /**
     * Show [com.google.android.material.snackbar.Snackbar] and a function button
     */
    fun View.showSnackbar(
        @StringRes text: Int,
        shortDuration: Boolean = true,
        @StringRes button: Int,
        func: () -> Unit
    ) {
        snackbar(
            this,
            this.context.getString(text),
            shortDuration,
            this.context.getString(button),
            func
        )
    }

    /**
     * Draw an underline on TexView
     */
    fun TextView.drawUnderline() {
        applySpan(this, UnderlineSpan())
    }

    /**
     * Apply  [Typeface.BOLD] on the text
     */
    fun TextView.bold() {
        applySpan(this, StyleSpan(Typeface.BOLD))
    }

    /**
     * Apply  [Typeface.NORMAL] on the text
     */
    fun TextView.normal() {
        applySpan(this, StyleSpan(Typeface.NORMAL))
    }

    /**
     * Apply  [Typeface.ITALIC] on the text
     */
    fun TextView.italic() {
        applySpan(this, StyleSpan(Typeface.ITALIC))
    }

    /**
     * Call it to hide the keyboard
     */
    fun View?.hideKeyboard() {
        this?.let {
            val imm = it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    /**
     * Get the text of EditText.
     */
    fun EditText.text(): String = this.text.toString()

    fun RecyclerView.listenScroll(func: () -> Unit) {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                val lastPosition = layoutManager.findLastVisibleItemPosition()

                if (recyclerView.adapter?.itemCount == 1) return

                if (lastPosition == recyclerView.adapter?.itemCount?.minus(1)) func()
            }
        })
    }
}