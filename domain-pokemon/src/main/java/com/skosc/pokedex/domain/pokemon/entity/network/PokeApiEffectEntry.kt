package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiEffectEntry(
    @SerialName("effect")
    val effect: String,
    @SerialName("short_effect")
    val shortEffect: String,
    @SerialName("language")
    val language: PokeApiLanguage
)