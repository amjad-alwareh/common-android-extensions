package com.amjadalwareh.cake

import android.Manifest
import android.annotation.SuppressLint
import android.app.*
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.hardware.SensorManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.net.ConnectivityManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.*
import android.os.storage.StorageManager
import android.provider.Settings
import android.provider.Settings.*
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.telephony.euicc.EuiccManager
import android.util.Base64
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.annotation.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amjadalwareh.cake.StringKt.isValidUrl
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object ContextKt {

    /**
     * Check if passed version of API is equal or lower than device api.
     */
    fun Context.checkApi(version: Int) = Build.VERSION.SDK_INT >= version

    /**
     * Check if api is equal or higher then call [func].
     */
    inline fun doIfApi(version: Int, func: () -> Unit) {
        if (Build.VERSION.SDK_INT >= version) func()
    }

    /**
     * Check if api is equal or higher then call [func], otherwise call [otherwise].
     */
    inline fun doIfApiOtherwise(version: Int, func: () -> Unit, otherwise: () -> Unit) {
        if (Build.VERSION.SDK_INT >= version) func()
        else otherwise()
    }

    /**
     * Show [android.widget.Toast] with long duration.
     */
    fun Context.longToast(@StringRes string: Int) {
        Utils.toast(this, getString(string), false)
    }

    /**
     * Show [android.widget.Toast] with short duration.
     */
    fun Context.shortToast(@StringRes string: Int) {
        Utils.toast(this, getString(string))
    }

    /**
     * Show [android.widget.Toast] with long duration
     */
    fun Context.longToast(string: String) {
        Utils.toast(this, string, false)
    }

    /**
     * Show [android.widget.Toast] with short duration
     */
    fun Context.shortToast(string: String) {
        Utils.toast(this, string)
    }

    /**
     * Check if @param [permission] is granted or not
     */
    fun Context.isGranted(permission: String): Boolean = ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    /**
     * Get the hash key of your app.
     */
    fun Context.getHashKey(): String {
        return try {
//            doIfApi(Build.VERSION_CODES.P) {
//                val packageInfo: PackageInfo = this.packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
//                if (packageInfo == null || packageInfo.signingInfo == null) {
//                }
//                if (packageInfo.signingInfo.hasMultipleSigners()) {
//                    signatureDigest(packageInfo.signingInfo.apkContentsSigners)
//                } else {
//                    signatureDigest(packageInfo.signingInfo.signingCertificateHistory)
//                }
//            }

            val info: PackageInfo = this.packageManager.getPackageInfo(this.packageName, PackageManager.GET_SIGNATURES)
            var hashKey = ""
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                hashKey = String(Base64.encode(md.digest(), 0))
            }
            hashKey
        } catch (e: NoSuchAlgorithmException) {
            ""
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * A new [Intent] of passed [T].
     */
    inline fun <reified T : Context> Context.intent() = Intent(this, T::class.java)

    /**
     * get color from resources.
     */
    fun Context.color(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)

    /**
     * get drawable from resources.
     */
    fun Context.drawable(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(this, id)

    /**
     * get animation from resources.
     */
    fun Context.animation(@AnimRes id: Int): Animation = AnimationUtils.loadAnimation(this, id)

    /**
     * get integer from resources.
     */
    fun Context.integer(@IntegerRes id: Int): Int = this.resources.getInteger(id)

    /**
     * get boolean from resources.
     */
    fun Context.boolean(@BoolRes id: Int): Boolean = this.resources.getBoolean(id)

    val Context.batteryPercentage: Int
        get() = manager<BatteryManager>().getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

    val Context.batterySaverEnable: Boolean
        get() = manager<PowerManager>().isPowerSaveMode

    fun Context.share(title: String, uriToImage: Uri) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uriToImage)
            type = "image/jpeg"
        }
        startActivity(Intent.createChooser(shareIntent, title))
    }

    fun Context.share(text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    fun Context.makeCall(mobilePhone: String) {
        if (!isGranted(Manifest.permission.CALL_PHONE)) return
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$mobilePhone")))
    }

    fun Context.call(mobilePhone: String) {
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$mobilePhone")))
    }

    fun Context.browse(url: String) {
        if (!url.isValidUrl()) return
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun Context.settings() {
        startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    fun Context.pickWifi() {
        if (checkApi(Build.VERSION_CODES.Q)) startActivity(Intent(Panel.ACTION_WIFI))
        else startActivity(Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
    }

    fun Context.mobile() {
        startActivity(Intent(ACTION_DATA_ROAMING_SETTINGS))
    }

    fun Context.volume() {
        doIfApi(Build.VERSION_CODES.Q) { startActivity(Intent(Panel.ACTION_VOLUME)) }
    }

    fun Context.displaySettings() {
        startActivity(Intent(Settings.ACTION_DISPLAY_SETTINGS))
    }

    fun Context.locationSettings() {
        startActivity(Intent(ACTION_LOCATION_SOURCE_SETTINGS))
    }

    fun Context.appSettings(packageName: String) {
        startActivity(Intent(ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")))
    }

    /**
     * Get an instance of required System service.
     */
    @SuppressLint("NewApi")
    inline fun <reified T : Any> Context.manager(): T =
        getSystemService(
            when (T::class.java) {
                WindowManager::class.java -> Context.WINDOW_SERVICE
                LayoutInflater::class.java -> Context.LAYOUT_INFLATER_SERVICE
                ActivityManager::class.java -> Context.ACTIVITY_SERVICE
                PowerManager::class.java -> Context.POWER_SERVICE
                AlarmManager::class.java -> Context.ALARM_SERVICE
                NotificationManager::class.java -> Context.NOTIFICATION_SERVICE
                KeyguardManager::class.java -> Context.KEYGUARD_SERVICE
                LocationManager::class.java -> Context.LOCATION_SERVICE
                SearchManager::class.java -> Context.SEARCH_SERVICE
                SensorManager::class.java -> Context.SENSOR_SERVICE
                StorageManager::class.java -> Context.STORAGE_SERVICE
                Vibrator::class.java -> Context.VIBRATOR_SERVICE
                ConnectivityManager::class.java -> Context.CONNECTIVITY_SERVICE
                WifiManager::class.java -> Context.WIFI_SERVICE
                AudioManager::class.java -> Context.AUDIO_SERVICE
                MediaRouter::class.java -> Context.MEDIA_ROUTER_SERVICE
                TelephonyManager::class.java -> Context.TELEPHONY_SERVICE
                SubscriptionManager::class.java -> Context.TELEPHONY_SUBSCRIPTION_SERVICE
                CarrierConfigManager::class.java -> Context.CARRIER_CONFIG_SERVICE
                EuiccManager::class.java -> Context.EUICC_SERVICE
                InputMethodManager::class.java -> Context.INPUT_METHOD_SERVICE
                UiModeManager::class.java -> Context.UI_MODE_SERVICE
                DownloadManager::class.java -> Context.DOWNLOAD_SERVICE
                BatteryManager::class.java -> Context.BATTERY_SERVICE
                JobScheduler::class.java -> Context.JOB_SCHEDULER_SERVICE
                NetworkStatsManager::class.java -> Context.NETWORK_STATS_SERVICE
                HardwarePropertiesManager::class.java -> Context.HARDWARE_PROPERTIES_SERVICE
                else -> ""
            }
        ) as T
}