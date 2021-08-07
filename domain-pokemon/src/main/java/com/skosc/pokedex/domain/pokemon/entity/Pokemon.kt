package com.skosc.pokedex.domain.pokemon.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val color: PokemonColor,
    val moves: List<PokemonMove>
)