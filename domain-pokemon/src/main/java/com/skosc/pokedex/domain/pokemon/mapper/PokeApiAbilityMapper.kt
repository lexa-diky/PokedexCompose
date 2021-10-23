package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.domain.pokemon.entity.*
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiAbility
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiEffectEntry

internal object PokeApiAbilityMapper {

    fun map(link: PokemonAbilityLink, ability: PokeApiAbility): PokemonAbility {
        return PokemonAbility(
            name = ability.name,
            names = ability.names.map { EntityName(it.name, it.language.name) },
            isHidden = link.isHidden,
            currentEffect = mapEffects(ability.currentEffectEntries),
            link = link
        )
    }

    private fun mapEffects(entries: List<PokeApiEffectEntry>): PokemonAbilityEntries {
        return PokemonAbilityEntries(
            entries.map { apiEntry ->
                PokemonAbilityEntry(
                    effect = apiEntry.effect,
                    shortEffect = apiEntry.shortEffect,
                    language = Language.values().firstOrNull { it.locale.toLanguageTag() == apiEntry.language.name } ?: Language.UNKNOWN
                )
            }
        )
    }
}