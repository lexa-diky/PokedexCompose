package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.theme.CardShape
import com.skosc.pokedex.uikit.theme.ChipShape
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.theme.ShadowWhite
import com.skosc.pokedex.uikit.titlecase

@Composable
fun PokemonCard(
    name: String,
    id: Int,
    tags: List<String>,
    imageUrl: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .background(backgroundColor, CardShape)
            .clip(CardShape)
            .padding(16.dp)
            .defaultMinSize(minHeight = 100.dp)
    ) {

        val (nameRef, idRef, typeListRef, pokeballRef) = createRefs()

        this.createHorizontalChain(nameRef, idRef, chainStyle = ChainStyle.SpreadInside)

        Text(
            text = name.titlecase(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(nameRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )

        Text(
            text = "#00$id",
            color = Color.Black.copy(alpha = 0.2f),
            modifier = Modifier.constrainAs(idRef) {
                start.linkTo(nameRef.end, margin = 16.dp)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )

        Column(
            modifier = Modifier.constrainAs(typeListRef) {
                start.linkTo(parent.start)
                top.linkTo(nameRef.bottom, margin = 4.dp)
            }
        ) {
            tags.forEachIndexed { idx, type ->
                TypeChip(type)
                if (tags.lastIndex != id) {
                    Spacer(modifier = Modifier.size(4.dp))
                }
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
                ),
                contentDescription = name,
                modifier = Modifier
                    .size(90.dp)
                    .offset(26.dp, 26.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_pokeball_filed),
                contentDescription = "pokeball icon",
                colorFilter = ColorFilter.tint(ShadowWhite),
                modifier = Modifier
                    .size(80.dp)
                    .offset(34.dp, 34.dp)
            )
        }
    }
}

@Composable
private fun TypeChip(type: String) {
    Text(
        text = type,
        fontSize = 11.sp,
        color = Color.White,
        modifier = Modifier
            .background(
                Color.White.copy(alpha = 0.2f),
                ChipShape
            )
            .padding(8.dp)
    )
}

@Composable
@Preview(showBackground = true)
private fun PokemonCardPreview() {
    Box(modifier = Modifier.width(200.dp)) {
        PokemonCard(
            name = "MegaSuperPikachu",
            id = 0,
            tags = listOf("Grass", "Fire"),
            backgroundColor = PokeColor.Teal,
            imageUrl = ""
        )
    }
}