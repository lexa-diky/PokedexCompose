package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiPokemon(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("sprites")
    val sprites: PokeApiPokemonSprite,
    @SerialName("types")
    val types: List<PokeApiPokemonTypeSlot>,
    @SerialName("moves")
    val moves: List<PokeApiPokemonInnerMove>
)