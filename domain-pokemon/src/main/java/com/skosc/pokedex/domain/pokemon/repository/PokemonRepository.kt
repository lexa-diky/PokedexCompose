package com.skosc.pokedex.domain.pokemon.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiResource
import com.skosc.pokedex.domain.pokemon.paging.LimitOffsetPokeApiDataSource
import com.skosc.pokedex.domain.pokemon.service.PokeApiService
import com.skosc.pokedex.core.network.PaginatedFlowReader
import com.skosc.pokedex.domain.pokemon.entity.*
import com.skosc.pokedex.domain.pokemon.mapper.*
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiAbilityMapper
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiItemMapper
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiMoveMapper
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiPokemonSpeciesMapper
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiTypeMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class PokemonRepository internal constructor(private val service: PokeApiService) {

    suspend fun getPokemonSpecies(id: Int): PokemonSpecies {
        return service.getPokemonSpecies(id)
            .let(PokeApiPokemonSpeciesMapper::map)
    }

    suspend fun getType(type: PokemonTypeExpected): PokemonType {
        return service.getType(type.defaultName)
            .let(PokeApiTypeMapper::map)
    }

    suspend fun getAbilities(abilities: List<PokemonAbilityLink>): List<PokemonAbility> = withContext(Dispatchers.IO) {
        abilities.map { async { it to service.getAbility(it.resource) } }
            .awaitAll()
            .map { (link, ability) -> PokeApiAbilityMapper.map(link, ability) }
    }

    suspend fun allTypes(): List<PokemonType> = withContext(Dispatchers.IO) {
        PokemonTypeExpected.values().map { async { getType(it) } }
            .awaitAll()
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
                prefetchDistance = LimitOffsetPokeApiDataSource.NETWORK_PAGE_SIZE * 5,
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