package com.amjadalwareh.cake

import android.content.res.Resources

object ConversionKt {

    /**
     * Convert from Fahrenheit to Celsius
     */
    val <T : Number> T.toCelsius: Double
        get() = Utils.round(this.toDouble().minus(32) * 5.0 / 9.0)

    /**
     * Convert from Celsius to Fahrenheit
     */
    val <T : Number> T.toFahrenheit: Double
        get() = Utils.round(this.toDouble().times(9.0 / 5.0) + 32)

    /**
     * Convert from Degree to Radian
     */
    val <T : Number> T.toRadian: Double
        get() = Utils.round(this.toDouble().times(Math.PI / 180))

    /**
     * Convert from Radian to Degree
     */
    val <T : Number> T.toDegree: Double
        get() = Utils.round(this.toDouble().times(180 / Math.PI))

    /**
     * Convert from Mile to Kilometer
     */
    val <T : Number> T.toKM: Double
        get() = Utils.round(this.toDouble().times(1.609))

    /**
     * Convert from Kilometer to Mile
     */
    val <T : Number> T.toMile: Double
        get() = Utils.round(this.toDouble().div(1.609))

    /**
     * Convert from Inch to Meter
     */
    val <T : Number> T.toMeter: Double
        get() = Utils.round(this.toDouble().div(39.37))

    /**
     * Convert from Meter to Inch
     */
    val <T : Number> T.toInch: Double
        get() = Utils.round(this.toDouble().times(39.37))

    /**
     * Convert the passed px to dp
     */
    val Int.toDp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    /**
     * Convert the passed dp to px
     */
    val Int.toPx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

}