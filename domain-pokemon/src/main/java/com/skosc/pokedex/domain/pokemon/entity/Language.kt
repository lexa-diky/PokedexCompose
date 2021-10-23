package com.skosc.pokedex.domain.pokemon.entity

import java.util.*

enum class Language(val locale: Locale) {
    ENGLISH(Locale.ENGLISH),
    RUSSIAN(Locale("ru")),
    UNKNOWN(Locale("ce"));

    companion object {

        val DEFAULT = ENGLISH
    }
}