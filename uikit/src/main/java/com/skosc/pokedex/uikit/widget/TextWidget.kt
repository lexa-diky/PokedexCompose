package com.skosc.pokedex.uikit.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.skosc.pokedex.uikit.theme.Link

@Composable
fun PokeHeader(text: String, modifier: Modifier = Modifier) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 32.sp, modifier = modifier)
}

@Composable
fun SubPokeHeader(text: String, modifier: Modifier = Modifier) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = modifier)
}

@Composable
fun PokeLink(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = Link, modifier = modifier)
}

@Composable
@Preview
private fun PreviewPokeHeader() {
    PokeHeader(text = "Hello world")
}