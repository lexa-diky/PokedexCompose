package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonMove

object PokeApiMoveMapper {

    fun map(pokeApiPokemonMove: PokeApiPokemonMove): PokemonMove {
        return PokemonMove(
            id = pokeApiPokemonMove.id,
            name = pokeApiPokemonMove.name,
            type = pokeApiPokemonMove.type.name
        )
    }
}