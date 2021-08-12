package com.skosc.pokedex.uikit.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OrderText(
    order: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.Black.copy(alpha = 0.2f)
) {
    Text(
        text = formatAsOrder(order),
        color = color,
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