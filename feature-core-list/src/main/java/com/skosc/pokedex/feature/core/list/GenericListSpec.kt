package com.skosc.pokedex.feature.core.list

import androidx.paging.PagingSource

data class GenericListSpec<T: Any>(
    val source: PagingSource<Int, T>,
    val itemMapper: (T) -> BaseListItem
)