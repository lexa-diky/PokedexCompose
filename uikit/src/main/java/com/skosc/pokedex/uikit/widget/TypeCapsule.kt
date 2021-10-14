package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.theme.PokeShapes
import com.skosc.pokedex.uikit.theme.PokedexTheme
import com.skosc.pokedex.uikit.util.dimmed

@Composable
fun TypeCapsule(icon: Painter, name: String, background: Color, multiplier: Float) {
    val nameShape = remember {
        PokeShapes.CapsuleShape.copy(topEnd = CornerSize(0.dp), bottomEnd = CornerSize(0.dp))
    }
    val multiBackground = remember(background) { background.dimmed() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(4.dp, PokeShapes.CapsuleShape)
            .background(multiBackground, PokeShapes.CapsuleShape)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .background(background, nameShape)
                .padding(4.dp)
        ) {
            Image(painter = icon, contentDescription = null, modifier = Modifier.size(20.dp))
            PokeSubLabel(
                text = name,
                secondary = true
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(4.dp)
                .wrapContentHeight()
        ) {
            PokeSubLabel(
                text = stringResource(id = R.string.uikit_type_capsule_multiplier, multiplier),
                secondary = true
            )
        }
    }
}

@Composable
@Preview()
private fun Preview_TypeCapsule() = PokedexTheme {
    TypeCapsule(
        icon = painterResource(id = R.drawable.ic_pokeball),
        name = "Pokeball",
        background = Color.Blue,
        multiplier = 0.5f
    )
}
