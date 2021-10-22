package com.skosc.pokedex.domain.pokemon.entity.network

internal data class PokeApiPokemonSpeciesSpec(
    val species: PokeApiPokemonSpecies,
    val pokemon: List<PokeApiPokemon>,
    val types: Map<PokeApiPokemon, List<PokeApiType>>
)