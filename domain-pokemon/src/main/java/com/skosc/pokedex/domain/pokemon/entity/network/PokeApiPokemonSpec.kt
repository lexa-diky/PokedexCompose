package com.skosc.pokedex.domain.pokemon.entity.network

internal data class PokeApiPokemonSpec(
    val pokemon: PokeApiPokemon,
    val species: PokeApiPokemonSpecies,
    val types: List<PokeApiType>
)