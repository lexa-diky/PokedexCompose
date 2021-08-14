package com.skosc.pokedex.domain.pokemon.entity

import com.skosc.pokedex.core.entity.ActiveRecord

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val color: PokemonColor,
    val moves: List<ActiveRecord<PokemonMove>>,
    val baseStats: List<PokemonStat>,
    val catchRate: Int,
    val flavorText: List<PokemonFlavorText>,
    val habitat: ActiveRecord<PokemonHabitat>,
    val generation: String
) {

    companion object {

        const val MAX_CATCH_RATE = 100
    }
}
