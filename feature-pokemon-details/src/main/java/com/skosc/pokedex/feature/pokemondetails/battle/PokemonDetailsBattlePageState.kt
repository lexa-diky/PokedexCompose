package com.skosc.pokedex.feature.pokemondetails.battle

import com.skosc.pokedex.domain.pokemon.entity.PokemonAbility

data class PokemonDetailsBattlePageState(
    val abilities: List<PokemonAbility>
)