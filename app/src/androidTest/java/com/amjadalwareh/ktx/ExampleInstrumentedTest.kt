package com.amjadalwareh.ktx

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.amjadalwareh.cake.CollectionKt.isNotNullOrEmpty
import com.amjadalwareh.cake.DataKt.isNull
import com.amjadalwareh.cake.DataKt.isPrime
import com.amjadalwareh.cake.FileKt.size
import com.amjadalwareh.cake.StringKt.applyMD5
import com.amjadalwareh.cake.StringKt.applySHA1
import com.amjadalwareh.cake.StringKt.isValidEmail
import com.amjadalwareh.cake.StringKt.isValidIP
import com.amjadalwareh.cake.StringKt.isValidUrl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.amjadalwareh.ktx", appContext.packageName)
    }

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
    fun testCollection() {
        assertEquals(ArrayList<Any?>().apply { add(null) }.isNotNullOrEmpty(), true)
    }

    @Test
    fun test_inNull() {
        assertEquals(null.isNull(), true)
    }

    @Test
    fun test_checkMD5() {
        assertEquals("amjad alwareh".applyMD5().toUpperCase(), "F9DCD3B9CA6A93CDA92DE9D30ABAB941")
    }

    @Test
    fun test_checkSHA1() {
        assertEquals("amjad alwareh".applySHA1().toUpperCase(), "F9702643D5E7C67D299079310CA736FC50E920D4")
    }

    @Test
    fun test_checkFileSize() {
        val temp: File = File.createTempFile("temp", ".txt")
        temp.writeText("AMJAD ALWAREH")
        assertNotEquals(temp.size, 0.0)
        temp.deleteOnExit()
    }

    @Test
    fun test_testIsPrime() {
        assertEquals(3.isPrime, true)
    }
}