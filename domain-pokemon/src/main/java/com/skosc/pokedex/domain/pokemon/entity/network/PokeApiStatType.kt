package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class PokeApiStatType {

    @SerialName("hp")
    Hp,
    @SerialName("attack")
    Attack,
    @SerialName("special-attack")
    SpAttack,
    @SerialName("defense")
    Defence,
    @SerialName("special-defense")
    SpDefence,
    @SerialName("speed")
    Speed
}