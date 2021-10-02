package com.skosc.pokedex.feature.core.list

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.uikit.localViewModel
import com.skosc.pokedex.uikit.theme.CardShape
import com.skosc.pokedex.uikit.theme.UIColor
import com.skosc.pokedex.uikit.widget.*
import com.skosc.pokedex.uikit.widget.PairTileLayout
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterIsInstance
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


        FixedPairTileLayout(
            displayItems = pagingItems,
            placeholderItems = placeholderItems,
            edgePadding = 16.dp,
            displayContent = { item ->
                PokemonCard(
                    name = item.name,
                    order = item.order,
                    tags = item.tags,
                    leftBackgroundColor = item.leftColor,
                    rightBackgroundColor = item.rightColor,
                    imageUrl = item.image,
                    modifier = Modifier
                        .width(200.dp)
                        .height(125.dp)
                        .clickable { onItemSelected(item.order) }
                        .padding(8.dp)
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