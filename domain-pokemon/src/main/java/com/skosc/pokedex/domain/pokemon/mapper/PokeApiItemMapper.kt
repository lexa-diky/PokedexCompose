package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.domain.pokemon.entity.EntityName
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiItem

object PokeApiItemMapper {

    fun map(item: PokeApiItem): PokemonItem {
        return PokemonItem(
            id = item.id,
            sprite = item.sprites.default,
            names = item.names.map { EntityName(it.name, it.language.name) }
        )
    }
}