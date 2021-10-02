package com.skosc.pokedex.uikit.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Link = Color(0xFF4286F4)
val ShadowWhite = Color.White.copy(alpha = 0.2f)
val ShadowWitheBackground = Color(0x41FFFFFF)

object UIColor {
    val Background = Color(0xFFF7F7F7)
    val BackgroundAccent = Color(0xFFFFFFFF)
    val ShadowBlack = Color.Black.copy(alpha = 0.4f)
    val ShadowGray = Color.Black.copy(alpha = 0.1f)
    val ShadowTransparent = Color.Black.copy(alpha = 0.03f)
    val TextBlack = Color.Black
    val TextGray = Color.Gray

    object Accent {
        val Purple = Color(0xFF6200EE)
        val Teal = Color(0xFF03DAC5)
        val Yellow = Color(0xFFFFDE00)
        val Red = Color(0xFFF73535)
        val Green = Color(0xFF6AD30D)
        val Magenta = Color(0xFF9E0DD3)
        val SoftSwampGreen = Color(0xFF43DBB0)

        val Default = Purple
    }
}

object PokemonColor {
    val Black = Color(0xFF444444)
    val Blue = Color(0xFF6AD2EE)
    val Brown = Color(0xFFCF9F6F)
    val Gray = Color(0xFF4A6B66)
    val Green = Color(0xFF64D364)
    val Pink = Color(0xFFE690A3)
    val Purple = Color(0xFFC183C1)
    val Red = Color(0xFFEC8484)
    val White = Color(0xFF1C9E84)
    val Yellow = Color(0xFFFFDE00)

    val all = listOf(
        Black,
        Blue,
        Brown,
        Gray,
        Purple,
        Green,
        Purple,
        Pink,
        Purple,
        Red,
        White,
        Yellow,
    )

    val random get() = all.random()
}