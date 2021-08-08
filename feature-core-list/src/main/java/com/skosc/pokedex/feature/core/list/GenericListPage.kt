package com.skosc.pokedex.feature.core.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.uikit.localViewModel
import com.skosc.pokedex.uikit.widget.PairTileLayout
import com.skosc.pokedex.uikit.widget.PokemonCard
import com.skosc.pokedex.uikit.widget.RootLayout

@Composable
fun <T: Any> GenericItemListPage(header: String, spec: GenericListSpec<T>) {
    val viewModel = localViewModel { GenericListPageViewModel(spec) }
    val items = remember { viewModel.items() }
    val pagingItems = items.collectAsLazyPagingItems()

    RootLayout(header = header) {
        item { Spacer(modifier = Modifier.size(16.dp)) }

        PairTileLayout(items = pagingItems) { idx, item ->
            PokemonCard(
                name = item.name,
                id = item.order,
                tags = item.tags,
                backgroundColor = item.color,
                imageUrl = item.image,
                modifier = Modifier.width(200.dp)
            )
        }
    }
}


@Composable
inline fun <reified T: Any> GenericItemListPage(header: String, noinline mapper: (T) -> BaseListItem) {
    val spec = rememberGenericListSpec(mapper)
    GenericItemListPage(header, spec)
}