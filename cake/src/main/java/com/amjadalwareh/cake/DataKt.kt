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

    /**
     * Check if this number is even
     */
    val Int.isEven: Boolean
        get() = this % 2 == 0

    /**
     * Check if this number is odd
     */
    val Int.isOdd: Boolean
        get() = this % 2 == 1

    val Int.isPositive: Boolean
        get() = this > 0

    val Int.isNegative: Boolean
        get() = this < 0

    /**
     * Check if this number is prime
     */
    val Int.isPrime: Boolean
        get() {
            if (isNegative) return false
            var notPrime = false
            for (index in 2..this / 2){
                if (this % index == 0) {
                    notPrime = true
                    break
                }
            }
            return !notPrime
        }
}