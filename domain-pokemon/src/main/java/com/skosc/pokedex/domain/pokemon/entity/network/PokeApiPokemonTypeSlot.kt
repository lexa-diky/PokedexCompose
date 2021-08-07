package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiPokemonTypeSlot(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: PokeApiType
)