package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiPokemonSpecies(
    @SerialName("id")
    val id: Int,
    @SerialName("color")
    val color: Color? = Color("red"),
    @SerialName("generation")
    val generation: PokeApiGeneration,
    @SerialName("capture_rate")
    val captureRate: Int,
    @SerialName("habitat")
    val habitat: PokeApiHabitat,
    @SerialName("flavor_text_entries")
    val flavorText: List<PokeApiFlavorText>,
    @SerialName("evolution_chain")
    val evolutionChainRef: PokeApiLink,
    @SerialName("varieties")
    val varieties: List<PokeApiPokemonVariety>
) {

    @Serializable
    data class Color(@SerialName("name") val name: String)
}