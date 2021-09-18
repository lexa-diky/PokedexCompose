package com.skosc.pokedex.domain.pokemon.entity

import java.util.*

interface Named {

    fun name(locale: Locale): String
}

fun List<EntityName>.getForLocale(locale: Locale): EntityName {
    return firstOrNull { it.language == locale.language }
        ?: first { it.language == "en" }
}