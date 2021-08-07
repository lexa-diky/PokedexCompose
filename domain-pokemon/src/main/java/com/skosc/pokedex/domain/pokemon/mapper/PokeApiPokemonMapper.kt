package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonColor
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemon
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonSpec

object PokeApiPokemonMapper {

    fun map(spec: PokeApiPokemonSpec): Pokemon {
       return Pokemon(
           id = spec.pokemon.id,
           name = spec.pokemon.name,
           imageUrl = spec.pokemon.sprites.frontDefault,
           types = spec.pokemon.types.map { it.type.name },
           color = PokemonColor(spec.species.color.name),
           moves = spec.moves.map { PokeApiMoveMapper.map(it) }
       )
    }
}