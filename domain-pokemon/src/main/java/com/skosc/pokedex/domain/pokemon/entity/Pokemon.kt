package com.skosc.pokedex.domain.pokemon.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val baseStats: List<PokemonStat>,
) {

    companion object {

        const val MAX_CATCH_RATE = 100
    }
}
