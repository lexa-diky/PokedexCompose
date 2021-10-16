package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.core.util.applyIf
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.theme.PokeShapes
import com.skosc.pokedex.uikit.theme.PokedexTheme
import com.skosc.pokedex.uikit.util.dimmed

enum class CapsuleSize(
    val paddingVertical: Dp,
    val paddingHorizontal: Dp,
    val label: @Composable (String) -> Unit,
    val subLabel: @Composable (String) -> Unit
) {
    Large(
        paddingVertical = 6.dp,
        paddingHorizontal = 12.dp,
        label = { label -> PokeLabel(text = label, secondary = true) },
        subLabel = { label -> PokeSubLabel(text = label, secondary = true) }
    )
}

@Composable
fun Capsule(
    label: String,
    background: Color,
    size: CapsuleSize,
    modifier: Modifier = Modifier,
    subLabel: String? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .shadow(4.dp, PokeShapes.CapsuleShape)
            .applyIf(onClick != null) { clickable { onClick?.invoke() } }
            .background(background, PokeShapes.CapsuleShape)
            .padding(vertical = size.paddingVertical, horizontal = size.paddingHorizontal)
            .then(modifier)
    ) {
        size.label(label)
        if (subLabel != null) {
            size.subLabel(subLabel)
        }
    }
}

@Composable
@Preview
private fun Preview_Capsule() = PokedexTheme {
    Capsule(label = "Fast", background = Color.Red, CapsuleSize.Large)
}
