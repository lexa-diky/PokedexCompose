package com.skosc.pokedex.feature.core.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GenericListPageViewModel<T : Any>(private val spec: GenericListSpec<T>) : ViewModel() {

    fun items(): Flow<PagingData<BaseListItem>> {
        return createPager().flow
            .cachedIn(viewModelScope)
            .map { data ->
                data.map { item -> spec.itemMapper(item) }
            }
    }

    private fun createPager(): Pager<Int, T> = Pager(
        pagingSourceFactory = { spec.source },
        config = PagingConfig(pageSize = 10)
    )
}