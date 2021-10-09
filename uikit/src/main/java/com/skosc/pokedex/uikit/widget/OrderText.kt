package com.skosc.pokedex.uikit.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.theme.LocalColoristic

@Composable
fun OrderText(
    order: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = formatAsOrder(order),
        color = LocalColoristic.current.textSecondaryAccent,
        modifier = modifier
    )
}

private fun formatAsOrder(order: Int): String {
    return when {
        order < 10 -> "#00$order"
        order < 100 -> "#0$order"
        else -> "#$order"
    }
}