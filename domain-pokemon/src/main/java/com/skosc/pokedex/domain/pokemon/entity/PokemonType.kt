package com.skosc.pokedex.domain.pokemon.entity

import com.skosc.pokedex.core.localization.LocalizableResource
import com.skosc.pokedex.core.localization.Localization
import com.skosc.pokedex.domain.pokemon.util.getLocalized

data class PokemonType(
    val id: Int,
    val defaultName: String,
    val names: List<EntityName>,
    val relations: List<PokemonWeaknessRelation>
): LocalizableResource {

    val expected: PokemonTypeExpected = PokemonTypeExpected.from(defaultName)

    override fun getLocalized(localization: Localization): String {
        return names.getLocalized(localization)
    }
}