package com.skosc.pokedex.domain.pokemon.entity

data class PokemonMove(
    val id: Int,
    val name: String,
    val type: PokemonType
)