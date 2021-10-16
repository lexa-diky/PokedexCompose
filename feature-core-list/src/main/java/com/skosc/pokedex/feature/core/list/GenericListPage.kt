package com.skosc.pokedex.feature.core.list

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.uikit.localViewModel
import com.skosc.pokedex.uikit.theme.ColorDef
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.widget.FixedPairTileLayout
import com.skosc.pokedex.uikit.widget.PokemonCard
import com.skosc.pokedex.uikit.widget.RootLayout
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
    val coloristic = LocalColoristic.current

    val viewModel = localViewModel { GenericListPageViewModel(spec) }
    val items = remember { viewModel.items() }

    val pagingItems = items.collectAsLazyPagingItems()
    val placeholderItems = remember { makePlaceholders() }.collectAsLazyPagingItems()

    RootLayout(header = header) { state ->

        item { Spacer(modifier = Modifier.size(16.dp)) }

        stickyHeader(SEARCH_ITEM_KEY) {
            SearchBlock(filters = spec.filters, scrollState = state)
        }

        FixedPairTileLayout(
            displayItems = pagingItems,
            placeholderItems = placeholderItems,
            edgePadding = 20.dp,
            displayContent = { item ->
                PokemonCard(
                    name = item.name,
                    order = item.id,
                    tags = item.tags,
                    leftBackgroundColor = coloristic.from(item.leftColor),
                    rightBackgroundColor = coloristic.from(item.rightColor),
                    imageUrl = item.image,
                    shimmer = item.isPlaceholder,
                    onClick = { if (!item.isPlaceholder) { onItemSelected(item.id) } },
                    modifier = Modifier
                        .width(200.dp)
                        .height(125.dp)
                        .padding(4.dp)
                )
            },
        )
    }
}

private fun makePlaceholders() = flowOf(PagingData.from((0..15).map {
    BaseListItem(
        id = 0,
        name = "...",
        tags = emptyList(),
        color = ColorDef.BackgroundShadow,
        image = "",
        isPlaceholder = true
    )
}))

@Composable
private fun <T> SearchBlock(filters: List<ListFilter<T>>, scrollState: LazyListState) {
    val shouldAddPadding = scrollState.firstVisibleItemIndex >= 1
    val topPadding by animateDpAsState(targetValue = if (shouldAddPadding) 40.dp else 0.dp)

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