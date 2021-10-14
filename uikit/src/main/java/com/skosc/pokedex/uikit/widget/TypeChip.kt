package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.core.util.applyIf
import com.skosc.pokedex.uikit.theme.PokeShapes

@Composable
fun TypeChip(type: String, modifier: Modifier = Modifier, onClick: (() -> Unit)? = null) {
    PokeSubLabel(
        text = type,
        secondary = true,
        modifier = Modifier
            .background(
                Color.White.copy(alpha = 0.2f),
                PokeShapes.ChipShape
            )
            .clip(PokeShapes.ChipShape)
            .applyIf(onClick != null) { clickable { onClick?.invoke() } }
            .padding(8.dp)
            .then(modifier)
    )
}
