package com.skosc.pokedex.feature.core.list

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.uikit.localViewModel
import com.skosc.pokedex.uikit.widget.*
import com.skosc.pokedex.uikit.widget.filter.FieldFilterTag
import com.skosc.pokedex.uikit.widget.filter.FilterLayout
import com.skosc.pokedex.uikit.widget.filter.FilterTagContainer
import kotlinx.coroutines.flow.flowOf

private const val SEARCH_ITEM_KEY = "__SEARCH_ITEM_KEY"

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun <T : Any> GenericItemListPage(
    header: String,
    onItemSelected: (Int) -> Unit,
    spec: GenericListSpec<T>
) {
    val viewModel = localViewModel { GenericListPageViewModel(spec) }
    val items = remember { viewModel.items() }

    val pagingItems = items.collectAsLazyPagingItems()
    val placeholderItems = remember { makePlaceholders() }.collectAsLazyPagingItems()

    RootLayout(header = header) { state ->

        item { Spacer(modifier = Modifier.size(16.dp)) }

        Log.i("LOOOO","${state.layoutInfo.visibleItemsInfo.firstOrNull()?.index}")
        stickyHeader(SEARCH_ITEM_KEY) {
            SearchBlock(filters = spec.filters, scrollState = state)
        }

        Log.i("LSTATE", pagingItems.loadState.toString())


        FixedPairTileLayout(
            displayItems = pagingItems,
            placeholderItems = placeholderItems,
            edgePadding = 20.dp,
            displayContent = { item ->
                PokemonCard(
                    name = item.name,
                    order = item.id,
                    tags = item.tags,
                    leftBackgroundColor = item.leftColor,
                    rightBackgroundColor = item.rightColor,
                    imageUrl = item.image,
                    modifier = Modifier
                        .width(200.dp)
                        .height(125.dp)
                        .clickable { onItemSelected(item.id) }
                        .padding(4.dp)
                )
            },
        )
    }
}

private fun makePlaceholders() = flowOf(PagingData.from((0..15).map {
    BaseListItem(
        id = 0,
        name = "",
        tags = emptyList(),
        color = Color.Gray,
        image = ""
    )
}))

@Composable
private fun <T> SearchBlock(filters: List<ListFilter<T>>, scrollState: LazyListState) {
    val shouldAddPadding = scrollState.firstVisibleItemIndex >= 1
    val topPadding by animateDpAsState(targetValue = if (shouldAddPadding) 16.dp else 0.dp)

    FilterLayout(
        modifier = Modifier.padding(bottom = 16.dp, start = 24.dp, end = 24.dp, top = topPadding)
    ) {
        filters.forEach { filter ->
            FilterTagContainer(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                when (filter) {
                    is ListFilter.Field -> {
                        FieldFilterTag(placeholder = filter.title)
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : Any> GenericItemListPage(
    header: String,
    noinline onItemSelected: (Int) -> Unit = {},
    filters: List<ListFilter<T>> = emptyList(),
    mapper: BaseListItemMapper<T>,
) {
    val spec = rememberGenericListSpec(mapper, filters)
    GenericItemListPage(header, onItemSelected, spec)
}