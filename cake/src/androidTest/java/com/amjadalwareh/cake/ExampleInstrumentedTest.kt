package com.amjadalwareh.cake

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amjadalwareh.cake.FileKt.size
import com.amjadalwareh.cake.StringKt.isValidEmail
import com.amjadalwareh.cake.StringKt.isValidIP
import com.amjadalwareh.cake.StringKt.isValidUrl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun test_isValidEmail() {
        assertEquals("amjad.alwareh@gmail.com".isValidEmail(), true)
    }

    @Test
    fun test_isValidUrl() {
        assertEquals("www.amjadalwareh.com".isValidUrl(), true)
    }

    @Test
    fun test_isValidIP() {
        assertEquals("192.168.1.1".isValidIP(), true)
    }

    @Test
    fun test_checkFileSize() {
        val temp: File = File.createTempFile("temp", ".txt")
        temp.writeText("AMJAD ALWAREH")
        assertNotEquals(temp.size, 0.0)
        temp.deleteOnExit()
    }

}