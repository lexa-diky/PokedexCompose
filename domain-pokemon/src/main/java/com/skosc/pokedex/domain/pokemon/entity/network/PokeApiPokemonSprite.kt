package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiPokemonSprite(
    @SerialName("front_default")
    val frontDefault: String
)