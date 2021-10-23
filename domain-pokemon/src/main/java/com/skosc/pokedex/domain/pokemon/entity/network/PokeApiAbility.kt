package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class PokeApiAbility(
    @SerialName("name")
    val name: String,
    @SerialName("names")
    val names: List<PokeApiName>,
    @SerialName("effect_entries")
    val currentEffectEntries: List<PokeApiEffectEntry>
)