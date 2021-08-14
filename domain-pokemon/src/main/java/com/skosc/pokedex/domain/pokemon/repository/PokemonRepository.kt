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
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PokemonRepository internal constructor(private val service: PokeApiService) {

    suspend fun getPokemon(order: Int): Pokemon {
        return service.getPokemon(order)
            .let(PokeApiPokemonMapper::map)
    }

    fun getPokemonPagingSource(): PagingSource<Int, Pokemon> {
        return PokeApiDataSource({ offset, limit ->
            asyncLoad(offset, limit) { service.getPokemon(it) }
                .sortedBy { it.pokemon.id }
        }, PokeApiPokemonMapper::map)
    }

    fun getMovePagingSource(): PagingSource<Int, PokemonMove> {
        return PokeApiDataSource({ offset, limit ->
            asyncLoad(offset, limit) { service.getMove(it) }
                .sortedBy { it.id }
        }, PokeApiMoveMapper::map)
    }

    fun getItemPagingSource(): PagingSource<Int, PokemonItem> {
        return PokeApiDataSource({ offset, limit ->
            asyncLoad(offset, limit) { service.getItem(it) }
                .sortedBy { it.id }
        }, PokeApiItemMapper::map)
    }

    private suspend fun <T> asyncLoad(offset: Int, limit: Int, request: suspend (Int) -> T) = coroutineScope {
        (offset..(limit + offset)).map { idx -> async { request(idx) } }.awaitAll()
    }
}