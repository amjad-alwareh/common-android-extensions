package com.amjadalwareh.cake

import com.amjadalwareh.cake.CollectionKt.isNotNullOrEmpty
import com.amjadalwareh.cake.ConversionKt.toCelsius
import com.amjadalwareh.cake.ConversionKt.toFahrenheit
import com.amjadalwareh.cake.DataKt.isNull
import com.amjadalwareh.cake.DataKt.isPrime
import com.amjadalwareh.cake.StringKt.applyMD5
import com.amjadalwareh.cake.StringKt.applySHA1
import com.amjadalwareh.cake.StringKt.isNumeric
import com.amjadalwareh.cake.StringKt.removeVowels
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test_Temperature() {
        assertEquals(0.toFahrenheit.toInt(), 32)
        assertEquals(32.toCelsius.toInt(), 0)
    }

    @Test
    fun test_checkSHA1() {
        assertEquals("amjad alwareh".applySHA1().toUpperCase(), "F9702643D5E7C67D299079310CA736FC50E920D4")
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
    fun text_removeVowels() {
        Assert.assertNotEquals("Amjad".removeVowels(), "Amjad")
    }

    @Test
    fun test_testIsPrime() {
        assertEquals(3.isPrime, true)
    }

    @Test
    fun test_isNumeric() {
        assertEquals("123".isNumeric(), true)
    }
}