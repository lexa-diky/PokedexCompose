package com.skosc.pokedex.domain.pokemon.entity

data class PokemonSpecies(
    val id: Int,
    val color: PokemonColor,
    val catchRate: Int,
    val flavorText: List<PokemonFlavorText>,
    val generation: String,
    val varieties: List<Pokemon>
) {

    val defaultVariety: Pokemon by lazy { varieties.first() }
}
