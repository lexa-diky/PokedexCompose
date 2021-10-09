package com.skosc.pokedex.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skosc.pokedex.R
import com.skosc.pokedex.uikit.theme.*
import com.skosc.pokedex.uikit.widget.PokeLabel

@Composable
fun SmallCard(
    text: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .background(color, PokeCardShape)
            .clip(PokeCardShape)
            .clickable { onClick() }
    ) {
        val (textConst, topIconConst) = createRefs()

        PokeLabel(
            text = text,
            secondary = true,
            modifier = Modifier.constrainAs(textConst) {
                top.linkTo(parent.top, margin = 24.dp)
                bottom.linkTo(parent.bottom, margin = 24.dp)
                linkTo(parent.start, parent.end, startMargin = 32.dp, endMargin = 32.dp, bias = 0f)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_pokeball_filed),
            contentDescription = "pokeball icon",
            colorFilter = ColorFilter.tint(LocalColoristic.current.accentShadow),
            modifier = Modifier
                .size(64.dp)
                .offset(10.dp)
                .constrainAs(topIconConst) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        Row(modifier = Modifier.padding(32.dp)) {
            SmallCard("Pokemons", PokeColor.Accent.Magenta, {}, Modifier.weight(1f))
            Box(modifier = Modifier.weight(0.1f))
            SmallCard("Moves", PokeColor.Accent.Green, {}, Modifier.weight(1f))
        }
    }
}