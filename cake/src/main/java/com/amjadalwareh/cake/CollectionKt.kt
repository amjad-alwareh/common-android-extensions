package com.amjadalwareh.cake

import kotlin.collections.Collection

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

    inline fun <T: Any> Collection<T>.whenIsEmpty(func: (Collection<T>) -> Unit) {
        if (isEmpty()) func(this)
    }





}