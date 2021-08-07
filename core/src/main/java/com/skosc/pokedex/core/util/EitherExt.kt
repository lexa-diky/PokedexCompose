package com.skosc.pokedex.core.util

import arrow.core.Either

fun <A, B> Either<A, B>.unwrapToString(): String = when(this) {
    is Either.Left -> value.toString()
    is Either.Right -> value.toString()
}