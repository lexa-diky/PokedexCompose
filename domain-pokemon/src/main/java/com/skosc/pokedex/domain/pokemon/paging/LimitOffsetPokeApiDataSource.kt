package com.skosc.pokedex.domain.pokemon.paging

import androidx.core.net.toUri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

private typealias LimitOffsetLoadPokeApiPage = suspend (offset: Int, limit: Int) -> PokeApiPage
private typealias LimitOffsetLoadPokeApiData<T> = suspend (id: Int) -> T
private typealias LimitOffsetMapper<F, T> = (F) -> T

internal class LimitOffsetPokeApiDataSource<Api: Any, Domain: Any>(
    private val loadPokeApiPage: LimitOffsetLoadPokeApiPage,
    private val loadPokeApiData: LimitOffsetLoadPokeApiData<Api>,
    private val mapper: LimitOffsetMapper<Api, Domain>
) : PagingSource<Int, Domain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Domain> = withContext(Dispatchers.IO) {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        try {
            val apiRefs = loadPokeApiPage(offset, params.loadSize).results
            val apiObjects: List<Api> = apiRefs
                .map { async { loadPokeApiData(extractIdFormUrl(it.url)) } }
                .awaitAll()

            val domainObjects = apiObjects.map(mapper)
            val nextKey = if (apiRefs.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = domainObjects,
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

    private fun extractIdFormUrl(url: String): Int = url.toUri().lastPathSegment!!.toInt()

    companion object {

        const val NETWORK_PAGE_SIZE = 10
        private const val INITIAL_LOAD_SIZE = 0
    }
}