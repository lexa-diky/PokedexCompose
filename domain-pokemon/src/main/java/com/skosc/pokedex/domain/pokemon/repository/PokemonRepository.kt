package com.skosc.pokedex.domain.pokemon.repository

import androidx.paging.PagingSource
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiItemMapper
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiMoveMapper
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiPokemonMapper
import com.skosc.pokedex.domain.pokemon.paging.PokeApiDataSource
import com.skosc.pokedex.domain.pokemon.service.PokeApiService

class PokemonRepository(private val service: PokeApiService) {

    fun getPokemonPagingSource(): PagingSource<Int, Pokemon> {
        return PokeApiDataSource({ offset, limit ->
            offsetRange(offset, limit).map { service.getPokemon(it) }
        }, PokeApiPokemonMapper::map)
    }

    fun getMovePagingSource(): PagingSource<Int, PokemonMove> {
        return PokeApiDataSource({ offset, limit ->
            offsetRange(offset, limit).map { service.getMove(it) }
        }, PokeApiMoveMapper::map)
    }

    fun getItemPagingSource(): PagingSource<Int, PokemonItem> {
        return PokeApiDataSource({ offset, limit ->
            offsetRange(offset, limit).map { service.getItem(it) }
        }, PokeApiItemMapper::map)
    }

    private fun offsetRange(offset: Int, limit: Int) = (offset..(limit + offset))
}