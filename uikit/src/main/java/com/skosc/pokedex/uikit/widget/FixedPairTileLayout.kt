package com.skosc.pokedex.uikit.widget

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed

fun <Display : Any> LazyListScope.FixedPairTileLayout(
    displayItems: LazyPagingItems<Display>,
    displayContent: @Composable (Display) -> Unit,
    placeholderItems: LazyPagingItems<Display>,
    edgePadding: Dp = 0.dp,
    ) {
    if (displayItems.itemCount != 0) {
        DisplayState(displayItems, edgePadding, displayContent)
    } else {
        DisplayState(placeholderItems, edgePadding, displayContent)
    }
}

private fun <T : Any> LazyListScope.DisplayState(
    items: LazyPagingItems<T>,
    edgePadding: Dp,
    content: @Composable (T) -> Unit
) {
    val rows = (items.itemCount + 2 - 1) / 2
    items(rows) { rowIndex ->
        Row(
            modifier = Modifier.padding(horizontal = edgePadding)
        ) {
            for (columnIndex in 0 until 2) {
                val itemIndex = rowIndex * 2 + columnIndex
                if (itemIndex < items.itemCount && items[itemIndex] != null) {
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        content(items[itemIndex]!!)
                    }
                } else {
                    Spacer(Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}
