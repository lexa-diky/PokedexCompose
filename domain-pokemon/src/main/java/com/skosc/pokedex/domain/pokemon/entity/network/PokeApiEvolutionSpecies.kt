package com.skosc.pokedex.domain.pokemon.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URI
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.Path

@Serializable
class PokeApiEvolutionSpecies(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
) {

    val id: Int by lazy {
        val idStr: String = url.substring(url.lastIndexOf('/') + 1)
        idStr.toInt()
    }
}