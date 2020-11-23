package com.amjadalwareh.cake

import java.util.*

object CollectionKt {

    /**
     * Check that the passed collection is not null or empty
     */
    fun <T> Collection<T>?.isNotNullOrEmpty(): Boolean = this != null && !this.isEmpty()

    /**
     * Call the specified function [func] when the collection is not null and not empty.
     */
    inline fun <E : Any, T : Collection<E>> T?.whenNotNullNorEmpty(func: (T) -> Unit) {
        if (this != null && this.isNotEmpty()) func(this)
    }

    /**
     * call [func] when the collection is empty
     */
    inline fun <T : Any> Collection<T>.whenIsEmpty(func: (Collection<T>) -> Unit) {
        if (isEmpty()) func(this)
    }

    /**
     * call [func] when the collection is not empty
     */
    inline fun <T : Any> Collection<T>.whenIsNotEmpty(func: (Collection<T>) -> Unit) {
        if (!isEmpty()) func(this)
    }

    /**
     * Get object in the middle of the list, if size even get the first middle object :P
     */
    fun <T : Any> List<T?>.middle(): T? = when {
        isNullOrEmpty() -> null
        size % 2 == 0 -> get((size / 2) - 1)
        else -> get(size / 2)
    }

    /**
     * Replace a specified item wit
     */
    fun <T : Any> MutableList<T>.replace(index: Int, newItem: T) {
        if (index > size) return
        this[index] = newItem
    }

    fun MutableList<String>.toLowerCase(locale: Locale = Locale.getDefault()) {
        for (index in indices) this[index] = this[index].toLowerCase(locale)
    }

    fun MutableList<String>.toUpperCase(locale: Locale = Locale.getDefault()) {
        for (index in indices) this[index] = this[index].toUpperCase(locale)
    }


}