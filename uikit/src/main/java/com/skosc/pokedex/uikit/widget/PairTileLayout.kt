package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed

fun <T: Any> LazyListScope.PairTileLayout(
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
                        modifier = Modifier.padding(horizontal = 32.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            content(idx, prev!!)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            content(idx, item!!)
                        }
                    }

                    prev = null
                }
            }

            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}