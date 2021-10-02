package com.skosc.pokedex.feature.core.list

import androidx.paging.PagingSource
import com.skosc.pokedex.core.network.PaginatedFlowReader

data class GenericListSpec<T: Any>(
    val reader: PaginatedFlowReader<T>,
    val itemMapper: BaseListItemMapper<T>
)