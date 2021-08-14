package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiItem(
    @SerialName("id") val id: Int,
    @SerialName("names") val names: List<PokeApiName>,
    @SerialName("sprites") val sprites: PokeApiSprites
)