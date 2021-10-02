package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skosc.pokedex.uikit.theme.PokeShapes

@Composable
fun TypeChip(type: String) {
    Text(
        text = type,
        fontSize = 11.sp,
        color = Color.White,
        modifier = Modifier
            .background(
                Color.White.copy(alpha = 0.2f),
                PokeShapes.ChipShape
            )
            .padding(8.dp)
    )
}
