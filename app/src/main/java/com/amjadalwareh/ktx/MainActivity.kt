package com.amjadalwareh.ktx

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amjadalwareh.cake.CollectionKt.whenIsEmpty
import com.amjadalwareh.cake.ContextKt.getHashKey
import com.amjadalwareh.cake.ConversionKt.toInch
import com.amjadalwareh.cake.DataKt.whenIsCondition
import com.amjadalwareh.cake.FileKt.size
import com.amjadalwareh.cake.FileKt.sizeLargeThan
import com.amjadalwareh.cake.StringKt.applyMD5
import com.amjadalwareh.cake.StringKt.applySHA1
import com.amjadalwareh.cake.StringKt.applySHA256
import com.amjadalwareh.cake.ViewKt.fadeIn
import com.amjadalwareh.cake.ViewKt.fadeOut
import com.amjadalwareh.cake.ViewKt.gone
import com.amjadalwareh.cake.ViewKt.screenWidth
import com.amjadalwareh.cake.ViewKt.visible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_view_title.gone()
        Toast.makeText(this, "${this.screenWidth()}", Toast.LENGTH_LONG).show()

        Log.e("MD5", "AMJAD ALWAREH".applyMD5())
        Log.e("SHA1", "AMJAD ALWAREH".applySHA1())
        Log.e("SHA256", "AMJAD ALWAREH".applySHA256())
        Log.e("toDegree", 100.toInch.toString())
        Log.e("toDegree", 1.toInch.toString())
        text_view_title.visible()
        1.whenIsCondition(1 == 1) { Log.e("1", "1") }
        1.whenIsCondition(2 == 1) { Log.e("2", "2") }
        val temp: File = File.createTempFile("temp", ".txt")
        temp.writeText("AMJAD ALWAREH")
        Log.e("FILE", temp.size.toString())
        temp.sizeLargeThan(13.0, { Log.e("FILE", "LARGE") }, { Log.e("FILE", "LOWER") })

        Log.e("HASH", getHashKey())
        Snackbar.make(text_view_title, "a", Snackbar.LENGTH_SHORT).show()

        text_view_title.fadeOut()

//        "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png".download(this)
    }
}