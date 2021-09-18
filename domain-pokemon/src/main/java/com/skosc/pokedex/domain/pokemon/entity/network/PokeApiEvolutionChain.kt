package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokeApiEvolutionChain(
    @SerialName("chain") val chain: Inner
) {

    @Serializable
    data class Inner(
        @SerialName("evolves_to")
        val evolvesTo: Inner?,
        @SerialName("species")
        val species: PokeApiEvolutionSpecies
    )
}

fun PokeApiEvolutionChain.flatten(): List<PokeApiEvolutionNodePrefab> = chain.flatten()

private fun PokeApiEvolutionChain.Inner.flatten(): List<PokeApiEvolutionNodePrefab> {
    return evolvesTo?.flatten().orEmpty() + PokeApiEvolutionNodePrefab(this.species)
}
