package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiFlavorText(
    @SerialName("flavor_text")
    val text: String,
    @SerialName("language")
    val language: PokeApiLanguage,
    @SerialName("version")
    val version: PokeApiVersion
)