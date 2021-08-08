package com.skosc.pokedex.domain.pokemon.entity

import com.skosc.pokedex.core.entity.ActiveRecord

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val color: PokemonColor,
    val moves: List<ActiveRecord<PokemonMove>>
)