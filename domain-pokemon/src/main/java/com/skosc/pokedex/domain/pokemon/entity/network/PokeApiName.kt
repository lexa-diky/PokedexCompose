package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiName(
    val language: Language,
    val name: String
) {

    @Serializable
    data class Language(
        @SerialName("name") val name: String
    )
}