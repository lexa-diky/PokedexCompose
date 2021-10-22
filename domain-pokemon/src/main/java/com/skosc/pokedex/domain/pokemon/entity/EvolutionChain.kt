package com.skosc.pokedex.domain.pokemon.entity

data class EvolutionChain(
    val nodes: List<Node>
) {

    data class Node(
        val pokemon: Pokemon
    )
}