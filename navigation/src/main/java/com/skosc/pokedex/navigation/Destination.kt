package com.skosc.pokedex.navigation

interface Destination<T: Any> {

    val path: String

    val arguments: T
}