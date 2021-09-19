package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiLink(
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String
)