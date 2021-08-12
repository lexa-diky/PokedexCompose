package com.skosc.pokedex.uikit.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.theme.BarShape
import com.skosc.pokedex.uikit.theme.PokedexTheme
import com.skosc.pokedex.uikit.theme.Shapes

@Composable
fun FillBar(
    fill: Float,
    backgroundColor: Color,
    fillColor: Color,
    modifier: Modifier = Modifier,
) {
    val animatedFill by animateFloatAsState(fill)

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    backgroundColor, BarShape
                )
        ) {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = animatedFill)
                .fillMaxHeight()
                .background(
                    fillColor, BarShape
                )
        )
    }
}

@Composable
@Preview(name = "Fill Bar", showBackground = true)
private fun Preview_FillBar() {
    PokedexTheme {
        FillBar(
            fill = 0.33f,
            backgroundColor = Color.Gray,
            fillColor = Color.Cyan,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
    }
}