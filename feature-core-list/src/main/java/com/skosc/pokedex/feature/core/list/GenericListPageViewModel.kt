package com.skosc.pokedex.feature.core.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GenericListPageViewModel<T : Any>(private val spec: GenericListSpec<T>) : ViewModel() {

    fun items(): Flow<PagingData<BaseListItem>> {
        return spec.reader()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
            .map { data ->
                data.map { item -> spec.itemMapper(item) }
            }
    }
}