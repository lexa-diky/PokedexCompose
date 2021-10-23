package com.skosc.pokedex.domain.pokemon.entity

import com.skosc.pokedex.core.localization.LocalizableResourceGroup
import com.skosc.pokedex.core.localization.Localization

data class PokemonAbilityEntry(
    val effect: String,
    val shortEffect: String,
    val language: Language
)

data class PokemonAbilityEntries(val list: List<PokemonAbilityEntry>) : List<PokemonAbilityEntry> by list, LocalizableResourceGroup<PokemonAbilityEntry> {

    override fun getLocalized(localization: Localization): PokemonAbilityEntry {
        return list.firstOrNull {
            it.language.locale == localization.locale()
        }
            ?: list.firstOrNull {
                it.language.locale == Localization.DEFAULT.locale()
            }
            ?: list.first()
    }
}

