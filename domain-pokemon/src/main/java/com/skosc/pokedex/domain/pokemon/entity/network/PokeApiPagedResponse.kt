package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiPagedResponse<T>(
    @SerialName("results")
    val results: List<T>
)