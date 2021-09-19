package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.domain.pokemon.entity.EntityName
import com.skosc.pokedex.domain.pokemon.entity.PokemonType
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiType

internal object PokeApiTypeMapper {

    fun map(type: PokeApiType): PokemonType = PokemonType(
        id = type.id,
        defaultName = type.name,
        names = type.names.map { EntityName(it.name, it.language.name) }
    )
}