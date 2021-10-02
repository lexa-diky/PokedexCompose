package com.skosc.pokedex.feature.core.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.PagingSource
import com.skosc.pokedex.core.network.PaginatedFlowReader
import org.kodein.di.compose.LocalDI
import org.kodein.di.direct
import org.kodein.di.instance

@Composable
inline fun <reified T: Any> rememberGenericListSpec(
    mapper: BaseListItemMapper<T>
): GenericListSpec<T> {
    val reader = LocalDI.current.di.direct.instance<PaginatedFlowReader<T>>()
    val spec = remember {
        GenericListSpec(
            reader = reader,
            itemMapper = mapper
        )
    }

    return spec
}