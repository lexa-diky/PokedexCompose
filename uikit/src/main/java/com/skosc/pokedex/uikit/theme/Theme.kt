package com.skosc.pokedex.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PokeColor.Accent.SoftSwampGreen,
    primaryVariant = PokeColor.Accent.SoftSwampGreen,
    secondary = PokeColor.Accent.Green,
    background = PokeColor.Background.LightDefault
)

private val LightColorPalette = lightColors(
    primary = PokeColor.Accent.SoftSwampGreen,
    primaryVariant = PokeColor.Accent.SoftSwampGreen,
    secondary = PokeColor.Accent.Green,
    background = PokeColor.Background.LightDefault
)

@Composable
fun PokedexTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}