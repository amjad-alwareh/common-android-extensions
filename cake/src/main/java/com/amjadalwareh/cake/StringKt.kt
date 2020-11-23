package com.amjadalwareh.cake

import android.util.Patterns
import com.amjadalwareh.cake.Utils.hashWithAlgorithm
import com.amjadalwareh.cake.Utils.match

object StringKt {

    /**
     * Check this String is a valid email address
     */
    fun String.isValidEmail(): Boolean = match(Patterns.EMAIL_ADDRESS, this)

    /**
     * Check this String is a valid website
     */
    fun String.isValidUrl(): Boolean = match(Patterns.WEB_URL, this)

    /**
     * Check this String is a valid IP address
     */
    fun String.isValidIP(): Boolean = match(Patterns.IP_ADDRESS, this)

    /**
     * Check this String is a phone number
     */
    fun String.isValidPhone(): Boolean = match(Patterns.PHONE, this)

    /**
     * Apply MD5 to the given string
     */
    fun String.applyMD5(): String = hashWithAlgorithm(Utils.HashType.MD5)

    /**
     * Apply SHA1 to the given string
     */
    fun String.applySHA1(): String = hashWithAlgorithm(Utils.HashType.SHA1)

    /**
     * Apply SHA256 to the given string
     */
    fun String.applySHA256(): String = hashWithAlgorithm(Utils.HashType.SHA256)

    fun String.removeVowels(): String {
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        return this.filterNot { it in vowels }
    }
}