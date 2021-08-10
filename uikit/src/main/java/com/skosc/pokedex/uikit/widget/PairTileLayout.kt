package com.skosc.pokedex.uikit.widget

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed

fun <Display : Any> LazyListScope.PairTileLayout(
    displayItems: LazyPagingItems<Display>,
    displayContent: @Composable (Int, Display) -> Unit,
    placeholderItems: LazyPagingItems<Display>,
) {
    if (displayItems.itemCount != 0) {
        DisplayState(displayItems, displayContent)
    } else {
        DisplayState(placeholderItems, displayContent)
    }
}

private fun <T : Any> LazyListScope.DisplayState(
    items: LazyPagingItems<T>,
    content: @Composable (Int, T) -> Unit
) {
    var prev: T? = null
    itemsIndexed(items) { idx, item ->
        Column {
            if (idx.mod(2) == 0) {
                prev = item
            } else {
                if (prev != null) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentSize()
                        ) {
                            content(idx, prev!!)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentSize()
                        ) {
                            content(idx, item!!)
                        }
                    }
                    prev = null

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}