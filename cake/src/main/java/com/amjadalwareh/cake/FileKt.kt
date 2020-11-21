package com.amjadalwareh.cake

import java.io.File
import java.io.IOException

object FileKt {

    val File.size get() = if (!exists()) 0.0 else length().toDouble()
    val File.sizeInKb get() = size / 1024
    val File.sizeInMb get() = sizeInKb / 1024
    val File.sizeInGb get() = sizeInMb / 1024

    fun File.sizeLargeThan(limit: Double, largeOrEqual: () -> Unit, lower: () -> Unit) {
        if (!exists()) throw IOException("FILE NOT FOUND")
        if (size > limit) lower()
        else largeOrEqual()
    }
}