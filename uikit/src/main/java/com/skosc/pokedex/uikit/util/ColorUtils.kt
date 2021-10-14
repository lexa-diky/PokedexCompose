package com.skosc.pokedex.uikit.util

import androidx.compose.ui.graphics.Color

fun Color.dimmed(dx: Float = 0.5f) = copy(
    alpha = 1f,
    red = red * dx,
    blue = blue * dx,
    green = green * dx
)