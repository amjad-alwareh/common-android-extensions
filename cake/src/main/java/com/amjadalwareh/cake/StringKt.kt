package com.amjadalwareh.cake

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Patterns
import com.amjadalwareh.cake.ContextKt.checkApi
import com.amjadalwareh.cake.ContextKt.manager
import com.amjadalwareh.cake.Utils.hashWithAlgorithm
import com.amjadalwareh.cake.Utils.match
import java.util.*

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

    fun String.download(context: Context): Long {
        if (!this.isValidUrl()) return 0L
        if (!context.checkApi(Build.VERSION_CODES.M)) return -1L
        val manager = context.manager<DownloadManager>()
        val request = DownloadManager.Request(Uri.parse(this))
        request.setTitle("Attachment");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Attachment");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        return manager.enqueue(request)
    }
}