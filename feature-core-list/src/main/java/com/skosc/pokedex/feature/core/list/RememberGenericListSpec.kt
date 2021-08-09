package com.skosc.pokedex.feature.core.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.PagingSource
import org.kodein.di.compose.LocalDI
import org.kodein.di.direct
import org.kodein.di.instance

@Composable
inline fun <reified T: Any> rememberGenericListSpec(
    mapper: BaseListItemMapper<T>
): GenericListSpec<T> {
    val source = LocalDI.current.di.direct.instance<PagingSource<Int, T>>()
    val spec = remember {
        GenericListSpec(
            source = source,
            itemMapper = mapper
        )
    }

    return spec
}