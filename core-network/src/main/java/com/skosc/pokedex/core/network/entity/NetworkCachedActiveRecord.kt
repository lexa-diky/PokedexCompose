package com.skosc.pokedex.core.network.entity

import com.skosc.pokedex.core.entity.ActiveRecord
import java.lang.ref.SoftReference

internal class NetworkCachedActiveRecord<T: Any>(
    private val provider: suspend () -> T
) : ActiveRecord<T> {

    @Volatile
    private var cache: SoftReference<T>? = null


    @Synchronized
    override suspend fun get(): T {
        cache?.get()?.let { return it }

        val value = provider()
        cache = SoftReference(value)

        return value
    }
}

fun <T: Any> activeNetwork(provider: suspend () -> T): ActiveRecord<T> {
    return NetworkCachedActiveRecord(provider)
}
