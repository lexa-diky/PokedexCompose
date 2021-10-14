package com.skosc.pokedex.core.util

fun <F: T, T> F.applyIf(cond: Boolean, fn: F.(F) -> T): T {
    if (cond) {
        return fn(this)
    } else {
        return this
    }
}