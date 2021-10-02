package com.skosc.pokedex.uikit.widget.filter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun FilterLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
        modifier = modifier
    ) {
        content()
    }
}