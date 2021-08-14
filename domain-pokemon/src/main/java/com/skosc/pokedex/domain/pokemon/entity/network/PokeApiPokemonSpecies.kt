package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiPokemonSpecies(
    @SerialName("color")
    val color: Color? = Color("red"),
    @SerialName("generation")
    val generation: PokeApiGeneration,
    @SerialName("capture_rate")
    val captureRate: Int ,
    @SerialName("habitat")
    val habitat: PokeApiHabitat,
    @SerialName("flavor_text_entries")
    val flavorText: List<PokeApiFlavorText>

) {

    @Serializable
    data class Color(@SerialName("name") val name: String)
}