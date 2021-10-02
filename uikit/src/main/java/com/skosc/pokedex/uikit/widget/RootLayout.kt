package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val HEADER_KEY = "__ROOT_LAYOUT_HEADER_KEY"

@Composable
fun RootLayout(header: String, items: LazyListScope.(LazyListState) -> Unit) {

    Box {
        RotatingPokeBall(
            modifier = Modifier
                .size(200.dp, 200.dp)
                .align(Alignment.TopEnd)
                .offset(64.dp, (-64).dp)
        )

        val lazyState = rememberLazyListState()
        LazyColumn(
            state = lazyState,
            modifier = Modifier.fillMaxSize()
        ) {
            item(HEADER_KEY) {
                PokeHeader(header, modifier = Modifier.padding(start = 32.dp, top = 32.dp))
            }
            items(lazyState)
        }
    }
}