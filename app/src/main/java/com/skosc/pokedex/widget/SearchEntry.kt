package com.skosc.pokedex.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skosc.pokedex.R
import com.skosc.pokedex.uikit.theme.PokedexTheme
import com.skosc.pokedex.uikit.widget.PokeCardHeader

@Composable
fun SearchEntry(
    title: String,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        PokeCardHeader(
            text = title,
            modifier = Modifier.weight(1f)
                .align(Alignment.CenterVertically)
        )

        Image(
            painter = icon,
            contentDescription = title,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterVertically)
                .padding(4.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultSearchEntryPreview() {
    PokedexTheme {
        SearchEntry(
            title = "Pikachu",
            icon = painterResource(R.drawable.ic_pokeball),
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
                .height(height = 80.dp)

        )
    }
}