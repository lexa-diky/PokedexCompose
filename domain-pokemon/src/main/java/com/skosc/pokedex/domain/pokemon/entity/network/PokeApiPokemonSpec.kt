package com.skosc.pokedex.domain.pokemon.entity.network

import com.skosc.pokedex.core.entity.ActiveRecord

data class PokeApiPokemonSpec(
    val pokemon: PokeApiPokemon,
    val species: PokeApiPokemonSpecies,
    val moves: List<ActiveRecord<PokeApiPokemonMove>>
)