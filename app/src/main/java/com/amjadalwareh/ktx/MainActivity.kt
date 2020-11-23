package com.amjadalwareh.ktx

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amjadalwareh.cake.CollectionKt.middle
import com.amjadalwareh.cake.CollectionKt.replace
import com.amjadalwareh.cake.CollectionKt.toLowerCase
import com.amjadalwareh.cake.CollectionKt.toUpperCase
import com.amjadalwareh.cake.ViewKt.glow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_view_title.glow()

        val strings = ArrayList<String>()
        strings.add("Amjad Alwareh")
        strings.add("b")
        strings.add("c")
        strings.add("d")
        val maxs = strings.maxOrNull()
//
//        list.toUpperCase()
//        list.toLowerCase()

        val list = listOf(10, 2, 33)
        val max: Int = list.maxOrNull() ?: 0
        Log.e("----------", "----------")
    }
}