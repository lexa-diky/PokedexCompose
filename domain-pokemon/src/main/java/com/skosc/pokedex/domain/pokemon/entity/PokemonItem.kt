package com.skosc.pokedex.domain.pokemon.entity

import java.util.*

data class PokemonItem(
    val id: Int,
    val sprite: String,
    val names: List<EntityName>
): Named {

    override fun name(locale: Locale): String {
        return names.first { it.language == "en" }.value
    }
}