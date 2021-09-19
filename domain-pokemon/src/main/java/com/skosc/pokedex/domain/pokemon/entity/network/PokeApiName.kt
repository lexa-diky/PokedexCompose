package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiName(
    @SerialName("language")
    val language: PokeApiLanguage,
    @SerialName("name")
    val name: String
)