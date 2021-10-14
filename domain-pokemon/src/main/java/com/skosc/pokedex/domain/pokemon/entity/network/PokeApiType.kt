package com.skosc.pokedex.domain.pokemon.entity.network

import com.skosc.pokedex.core.localization.LocalizableResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokeApiType(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("names")
    val names: List<PokeApiName>,
    @SerialName("damage_relations")
    val damageRelations: PokeApiDamageRelations
)