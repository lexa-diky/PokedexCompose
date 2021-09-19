package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiPokemonVariety(
    @SerialName("is_default")
    val isDefault: Boolean,
    @SerialName("pokemon")
    val pokemon: PokeApiLink
)