package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiPage(
    @SerialName("count")
    val count: Int,
    @SerialName("results")
    val results: List<Entry>
) {

    @Serializable
    data class Entry(
        @SerialName("url")
        val url: String
    )
}