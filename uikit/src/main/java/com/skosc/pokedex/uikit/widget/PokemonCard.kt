package com.skosc.pokedex.uikit.widget

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.modifier.halfBackground
import com.skosc.pokedex.uikit.modifier.shimmer
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.theme.PokeCardShape
import com.skosc.pokedex.uikit.titlecase

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PokemonCard(
    name: String,
    order: Int,
    tags: List<String>,
    imageUrl: String,
    leftBackgroundColor: Color,
    rightBackgroundColor: Color,
    modifier: Modifier = Modifier,
    shimmer: Boolean = false,
    onClick: () -> Unit
) {
    val animatedLeftBackgroundColor by animateColorAsState(leftBackgroundColor)
    val animatedRightBackgroundColor by animateColorAsState(rightBackgroundColor)

    ConstraintLayout(
        modifier = modifier
            .clip(PokeCardShape)
            .clickable { onClick() }
            .shimmer(shimmer)
            .halfBackground(animatedLeftBackgroundColor, animatedRightBackgroundColor)
            .padding(16.dp)
            .defaultMinSize(minHeight = 100.dp)
    ) {

        val (nameRef, idRef, typeListRef, pokeballRef) = createRefs()

        this.createHorizontalChain(nameRef, idRef, chainStyle = ChainStyle.SpreadInside)

        PokeCardHeader(
            text = name.titlecase(),
            secondary = true,
            modifier = Modifier
                .animateContentSize()
                .constrainAs(nameRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        OrderText(order = order, modifier = Modifier
            .constrainAs(idRef) {
                start.linkTo(nameRef.end, margin = 16.dp)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            })

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .animateContentSize()
                .constrainAs(typeListRef) {
                    start.linkTo(parent.start)
                    top.linkTo(nameRef.bottom, margin = 4.dp)
                }
        ) {
            tags.forEachIndexed { idx, type ->
                TypeChip(type)
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(pokeballRef) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {

            Image(
                painter = rememberImagePainter(
                    data = imageUrl
                ) {
                    fadeIn()
                },
                contentDescription = name,
                modifier = Modifier
                    .size(90.dp)
                    .offset(26.dp, 26.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_pokeball_filed),
                contentDescription = "pokeball icon",
                colorFilter = ColorFilter.tint(LocalColoristic.current.accentShadow),
                modifier = Modifier
                    .size(80.dp)
                    .offset(34.dp, 34.dp)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun PokemonCardPreview() {
    Box(modifier = Modifier.width(200.dp)) {
        PokemonCard(
            name = "MegaSuperPikachu",
            order = 0,
            tags = listOf("Grass", "Fire"),
            leftBackgroundColor = Color.Red,
            rightBackgroundColor = Color.Blue,
            imageUrl = "",
            onClick = {}
        )
    }
}