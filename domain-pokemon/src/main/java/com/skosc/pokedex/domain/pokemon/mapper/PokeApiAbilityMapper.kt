package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.domain.pokemon.entity.EntityName
import com.skosc.pokedex.domain.pokemon.entity.PokemonAbility
import com.skosc.pokedex.domain.pokemon.entity.PokemonAbilityLink
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiAbility

internal object PokeApiAbilityMapper {

    fun map(link: PokemonAbilityLink, ability: PokeApiAbility): PokemonAbility {
        return PokemonAbility(
            name = ability.name,
            names = ability.names.map { EntityName(it.name, it.language.name) },
            isHidden = link.isHidden
        )
    }
}