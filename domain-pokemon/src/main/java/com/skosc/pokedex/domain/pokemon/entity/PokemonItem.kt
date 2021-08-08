package com.skosc.pokedex.domain.pokemon.entity

data class PokemonItem(
    val id: Int,
    val sprite: String,
    val names: List<EntityName>
): Named {

    override val name: String by lazy {
        names.first { it.language == "en" }.value
    }
}