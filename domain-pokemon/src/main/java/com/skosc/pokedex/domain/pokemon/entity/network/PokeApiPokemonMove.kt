package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiPokemonMove(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("type") val type: PokeApiType,
    @SerialName("damage_class") val damageClass: PokeApiDamageClass
)