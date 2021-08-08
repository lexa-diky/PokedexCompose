package com.skosc.pokedex.domain.pokemon.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiItemMapper
import com.skosc.pokedex.domain.pokemon.service.PokeApiService

class PokemonItemDataSource(private val pokeApiService: PokeApiService) : PagingSource<Int, PokemonItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItem> {
        return try {
            val nextPage = params.key ?: 1

            val items = (nextPage..params.loadSize)
                .map { pokeApiService.getItem(it + 1) }
                .map { PokeApiItemMapper.map(it) }

            LoadResult.Page(
                data = items,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = params.loadSize + nextPage + 1
            )
        } catch (e: Exception) {
            Log.e("Pager", "err", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}