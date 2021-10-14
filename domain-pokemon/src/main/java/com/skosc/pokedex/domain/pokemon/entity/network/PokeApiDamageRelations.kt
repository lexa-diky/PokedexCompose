package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiDamageRelations(
    @SerialName("double_damage_from")
    val doubleDamageFrom: List<Binding>,
    @SerialName("double_damage_to")
    val doubleDamageTo: List<Binding>,
    @SerialName("half_damage_from")
    val halfDamageFrom: List<Binding>,
    @SerialName("half_damage_to")
    val halfDamageTo: List<Binding>,
    @SerialName("no_damage_from")
    val noDamageFrom: List<Binding>,
    @SerialName("no_damage_to")
    val noDamageTo: List<Binding>
) {

    @Serializable
    data class Binding(val name: String)
}