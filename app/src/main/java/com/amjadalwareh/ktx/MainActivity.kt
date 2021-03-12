package com.amjadalwareh.ktx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val s = NumberFormat.getCurrencyInstance().format("3")
        NumberFormat.getCurrencyInstance().currency?.displayName
    }
}