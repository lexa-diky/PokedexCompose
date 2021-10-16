package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiPokemonAbility(
    @SerialName("ability")
    val ability: PokeApiLink,
    @SerialName("is_hidden")
    val isHidden: Boolean
)
