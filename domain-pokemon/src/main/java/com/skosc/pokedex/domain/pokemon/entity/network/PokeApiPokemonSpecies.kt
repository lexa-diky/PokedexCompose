package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiPokemonSpecies(
    @SerialName("color")
    val color: Color
) {

    @Serializable
    data class Color(@SerialName("name") val name: String)
}