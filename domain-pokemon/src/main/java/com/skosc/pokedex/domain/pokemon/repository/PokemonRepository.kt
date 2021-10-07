package com.skosc.pokedex.domain.pokemon.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiResource
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiItemMapper
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiMoveMapper
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiPokemonSpeciesMapper
import com.skosc.pokedex.domain.pokemon.paging.LimitOffsetPokeApiDataSource
import com.skosc.pokedex.domain.pokemon.service.PokeApiService
import com.skosc.pokedex.core.network.PaginatedFlowReader

class PokemonRepository internal constructor(private val service: PokeApiService) {

    suspend fun getPokemonSpecies(id: Int): PokemonSpecies {
        return service.getPokemonSpecies(id)
            .let(PokeApiPokemonSpeciesMapper::map)
    }

    fun getPokemonSpeciesPaginatedReader(): PaginatedFlowReader<PokemonSpecies> = PaginatedFlowReader {
        Pager(
            config = PagingConfig(
                pageSize = LimitOffsetPokeApiDataSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LimitOffsetPokeApiDataSource(
                    loadPokeApiPage = { offset, limit -> service.getPaginated(PokeApiResource.PokemonSpecies, offset, limit) },
                    loadPokeApiData = { id -> service.getPokemonSpecies(id) },
                    mapper = PokeApiPokemonSpeciesMapper::map
                )
            }
        ).flow
    }

    fun getMovePaginatedReader(): PaginatedFlowReader<PokemonMove> = PaginatedFlowReader {
        Pager(
            config = PagingConfig(
                pageSize = LimitOffsetPokeApiDataSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LimitOffsetPokeApiDataSource(
                    loadPokeApiPage = { offset, limit -> service.getPaginated(PokeApiResource.Move, offset, limit) },
                    loadPokeApiData = { id -> service.getMove(id) },
                    mapper = PokeApiMoveMapper::map
                )
            }
        ).flow
    }

    fun getItemPaginatedReader(): PaginatedFlowReader<PokemonItem> = PaginatedFlowReader {
        Pager(
            config = PagingConfig(
                pageSize = LimitOffsetPokeApiDataSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LimitOffsetPokeApiDataSource(
                    loadPokeApiPage = { offset, limit -> service.getPaginated(PokeApiResource.Item, offset, limit) },
                    loadPokeApiData = { id -> service.getItem(id) },
                    mapper = PokeApiItemMapper::map
                )
            }
        ).flow
    }
}