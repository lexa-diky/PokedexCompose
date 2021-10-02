package com.skosc.pokedex.core.network

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

fun interface PaginatedFlowReader<T: Any> {

    operator fun invoke():  Flow<PagingData<T>>
}