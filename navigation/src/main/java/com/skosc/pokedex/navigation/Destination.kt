package com.skosc.pokedex.navigation

interface Destination {

    val path: String

    val requiredArguments: List<String> get() = emptyList()
}