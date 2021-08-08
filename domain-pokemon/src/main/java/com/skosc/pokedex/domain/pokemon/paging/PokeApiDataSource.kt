package com.skosc.pokedex.domain.pokemon.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

private typealias LoadPokeApiData<T> = suspend (offset: Int, limit: Int) -> List<T>
private typealias Mapper<F, T> = (F) -> T

class PokeApiDataSource<Api: Any, Domain: Any>(
    private val loadPokeApiData: LoadPokeApiData<Api>,
    private val mapper: Mapper<Api, Domain>
) : PagingSource<Int, Domain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Domain> {
        val position = params.key ?: 1
        val offset = if (params.key != null) ((position - 1) * params.loadSize) + 1 else 1
        return try {
            val jsonResponse = loadPokeApiData(offset, params.loadSize)
            val response = jsonResponse.map { mapper(it) }
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Domain>): Int? {
        return null
    }
}