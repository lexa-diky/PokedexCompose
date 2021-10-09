package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.theme.PokedexTheme

@Composable
fun StatBar(
    label: String,
    fill: Float,
    backgroundColor: Color = Color.Gray,
    fillColor: Color = Color.Blue,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        PokeLabel(
            text = label,
            secondary = false,
        )
        FillBar(
            fill = fill,
            backgroundColor = backgroundColor,
            fillColor = fillColor,
            modifier = Modifier.weight(1f).padding(start = 16.dp)
        )
    }
}


@Composable
@Preview(name = "Stat Bar", showBackground = true)
private fun Preview_StatBar() {
    PokedexTheme {
        StatBar(
            label = "HP",
            fill = 0.66f,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
    }
}