package com.skosc.pokedex.feature.core.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GenericListPageViewModel<T: Any>(private val spec: GenericListSpec<T>) : ViewModel() {

    private val _items: MutableStateFlow<PagingData<BaseListItem>> = MutableStateFlow(PagingData.empty())
    val items: StateFlow<PagingData<BaseListItem>> get() = _items

    init {
        viewModelScope.launch {
            createPager().flow.collect { pagingData ->
                val subPage = pagingData.map { spec.itemMapper(it) }
                _items.emit(subPage)
            }
        }
    }

    private fun createPager(): Pager<Int, T> = Pager(
        pagingSourceFactory = { spec.source },
        config = PagingConfig(15)
    )
}