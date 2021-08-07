package com.skosc.pokedex.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll

@SuppressWarnings("UNCHECKED_CAST")
suspend fun <T1, T2> CoroutineScope.await(
    deferred1: Deferred<T1>,
    deferred2: Deferred<T2>
): Pair<T1, T2> {
    val result = awaitAll(deferred1, deferred2)

    return (result[0] as T1) to (result[1] as T2)
}