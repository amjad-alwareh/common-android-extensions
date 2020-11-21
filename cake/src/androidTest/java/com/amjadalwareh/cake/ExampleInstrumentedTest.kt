package com.amjadalwareh.cake

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amjadalwareh.cake.ConversionKt.toFahrenheit
import com.amjadalwareh.cake.StringKt.isValidEmail

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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
        assertEquals("com.amjadalwareh.cake.test", appContext.packageName)
    }

    @Test
    fun testTemperature() {
        assertEquals(0.toFahrenheit.toInt(), 32)
    }

    @Test
    fun test_isValidEmail() {
        assertEquals("amjad.alwareh@gmail.com".isValidEmail(), true)
    }
}