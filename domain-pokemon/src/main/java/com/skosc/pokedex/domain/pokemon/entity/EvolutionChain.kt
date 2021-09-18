package com.skosc.pokedex.domain.pokemon.entity

import com.skosc.pokedex.core.entity.ActiveRecord

data class EvolutionChain(
    val nodes: List<Node>
) {

    data class Node(
        val pokemon: Pokemon
    )
}