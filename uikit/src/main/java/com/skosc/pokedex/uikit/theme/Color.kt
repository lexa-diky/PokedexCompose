package com.skosc.pokedex.uikit.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Background = Color(0xFFF7F7F7)
val BackgroundAccent = Color(0xFFFFFFFF)
val Link = Color(0xFF4286F4)
val ShadowWhite = Color.White.copy(alpha = 0.2f)
val ShadowWitheBackground = Color(0xFFEBEBEB)

object PokeColor {

    val Purple = Color(0xFF6200EE)
    val Teal = Color(0xFF03DAC5)
    val Yellow = Color(0xFFFFDE00)
    val Red = Color(0xFFF73535)
    val Green = Color(0xFF6AD30D)
    val Magenta = Color(0xFF9E0DD3)

    val all = listOf(Purple, Teal, Yellow, Red, Green, Magenta)

    fun fromName(name: String): Color = when(name) {
        "red" -> Red
        "green" -> Green
        else -> Teal
    }
}