package com.skosc.pokedex.domain.pokemon.entity.network

import com.skosc.pokedex.core.entity.ActiveRecord

internal data class PokeApiPokemonSpeciesSpec(
    val species: PokeApiPokemonSpecies,
    val pokemon: List<PokeApiPokemon>,
)