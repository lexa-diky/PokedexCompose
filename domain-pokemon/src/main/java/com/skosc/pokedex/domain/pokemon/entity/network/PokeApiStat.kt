package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiStat(
    @SerialName("base_stat")
    val baseStatValue: Int,
    @SerialName("stat")
    val stat: PokeApiStatInternal
)

@Serializable
internal class PokeApiStatInternal(
    @SerialName("name")
    val name: PokeApiStatType
)