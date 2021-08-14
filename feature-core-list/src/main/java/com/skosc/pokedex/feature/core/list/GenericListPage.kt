package com.skosc.pokedex.feature.core.list

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.uikit.localViewModel
import com.skosc.pokedex.uikit.theme.CardShape
import com.skosc.pokedex.uikit.theme.UIColor
import com.skosc.pokedex.uikit.widget.PairTileLayout
import com.skosc.pokedex.uikit.widget.PokemonCard
import com.skosc.pokedex.uikit.widget.RootLayout
import com.skosc.pokedex.uikit.widget.SearchField
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

        stickyHeader(SEARCH_ITEM_KEY) {
            SearchBlock(isScrollInProgress = state.isScrollInProgress)
        }

        Log.i("LSTATE", pagingItems.loadState.toString())


        PairTileLayout(
            displayItems = pagingItems,
            placeholderItems = placeholderItems,
            displayContent = { idx, item ->
                PokemonCard(
                    name = item.name,
                    order = item.order,
                    tags = item.tags,
                    backgroundColor = item.color,
                    imageUrl = item.image,
                    modifier = Modifier
                        .width(200.dp)
                        .height(125.dp)
                        .clickable { onItemSelected(item.order) }
                )
            },
        )
    }
}

private fun makePlaceholders() = flowOf(PagingData.from((0..15).map {
    BaseListItem(
        order = 0,
        name = "",
        tags = emptyList(),
        color = Color.Gray,
        image = ""
    )
}))

@Composable
private fun SearchBlock(isScrollInProgress: Boolean) {
    val topPadding by animateDpAsState(targetValue = if (isScrollInProgress) 16.dp else 0.dp)

    Box(
        modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = topPadding)
    ) {
        Column(
            modifier = Modifier
                .background(UIColor.BackgroundAccent, CardShape)
        ) {
            SearchField(
                onQueryUpdated = {},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
inline fun <reified T : Any> GenericItemListPage(
    header: String,
    noinline onItemSelected: (Int) -> Unit = {},
    mapper: BaseListItemMapper<T>,
) {
    val spec = rememberGenericListSpec(mapper)
    GenericItemListPage(header, onItemSelected, spec)
}