package com.skosc.pokedex.domain.pokemon.entity.network

internal enum class PokeApiResource(val id: String) {
    PokemonSpecies("pokemon-species"),
    Move("move"),
    Item("item"),
    Type("type")
}