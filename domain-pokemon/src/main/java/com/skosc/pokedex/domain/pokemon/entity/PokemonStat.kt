package com.skosc.pokedex.domain.pokemon.entity

data class PokemonStat(
    val value: Int,
    val type: PokemonStatType
) {

    companion object {

        const val MAX_VALUE = 255
    }
}