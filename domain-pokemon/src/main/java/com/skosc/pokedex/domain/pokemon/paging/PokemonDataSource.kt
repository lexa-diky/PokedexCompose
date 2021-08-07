package com.skosc.pokedex.domain.pokemon.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiPokemonMapper
import com.skosc.pokedex.domain.pokemon.service.PokeApiService

class PokemonDataSource(private val pokeApiService: PokeApiService) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val nextPage = params.key ?: 1

            val pokemon = (nextPage..params.loadSize)
                .map { pokeApiService.getPokemon(it + 1) }
                .map { PokeApiPokemonMapper.map(it) }

            LoadResult.Page(
                data = pokemon,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = params.loadSize + nextPage + 1
            )
        } catch (e: Exception) {
            Log.e("Pager", "err", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}