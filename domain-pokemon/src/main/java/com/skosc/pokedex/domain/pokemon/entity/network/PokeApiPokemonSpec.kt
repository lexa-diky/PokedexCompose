package com.skosc.pokedex.domain.pokemon.entity.network

data class PokeApiPokemonSpec(
    val pokemon: PokeApiPokemon,
    val species: PokeApiPokemonSpecies,
    val moves: List<PokeApiPokemonMove>
)