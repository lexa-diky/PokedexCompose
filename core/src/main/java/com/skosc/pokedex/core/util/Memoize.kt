package com.skosc.pokedex.core.util

import android.util.LruCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

object Memoize {

    suspend operator fun <I: Any, O: Any> invoke(cache: MutableMap<Any, Any>, input: I, fn: suspend CoroutineScope.() -> O): O = coroutineScope {
        if (input in cache) {
            return@coroutineScope cache[input] as O
        } else {
            val computed = fn()
            cache[input] = computed
            computed
        }
    }
}