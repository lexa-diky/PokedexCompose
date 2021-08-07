package com.skosc.pokedex.repository

import androidx.compose.ui.graphics.Color
import com.skosc.pokedex.uikit.theme.PokeColor

class ColorPalletResolver {

    fun getColor(any: Any): Color {
        val colors = PokeColor.all
        val hash = System.identityHashCode(any)
        val position = hash.mod(colors.size)
        return colors[position]
    }
}