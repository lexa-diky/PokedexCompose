package com.skosc.pokedex.uikit.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.skosc.pokedex.uikit.theme.PokeColor

@Composable
fun PokeHeader(text: String, modifier: Modifier = Modifier) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 32.sp, modifier = modifier)
}

@Composable
fun SubPokeHeader(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = color,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = modifier
    )
}

@Composable
fun PokeLink(text: String, modifier: Modifier = Modifier) {
    Text(text = text, color = PokeColor.Link, modifier = modifier)
}

@Composable
fun PokeCardHeader(
    text: String,
    color: Color = Color.White,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier
    )
}

@Composable
fun PokeCardSubHeader(
    text: String,
    color: Color = Color.White,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier
    )
}

@Composable
fun ListItemText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = PokeColor.TextBlack,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        text = text,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier,
        fontSize = 16.sp
    )
}

@Composable
@Preview
private fun PreviewPokeHeader() {
    PokeHeader(text = "Hello world")
}