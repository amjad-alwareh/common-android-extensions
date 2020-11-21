package com.amjadalwareh.cake

object DataKt {

    /**
     * Check if null
     */
    fun Any?.isNull(): Boolean = this == null

    /**
     * A workaround to pass the null check
     */
    fun <T> T?.default(default: T): T = this ?: default

    /**
     *
     */
    inline fun <E : Any> E.whenIsCondition(condition: Boolean, func: (E) -> Unit) {
        if (condition) func(this)
    }

    inline fun <reified T> T.tag(): String = T::class.java.simpleName
}