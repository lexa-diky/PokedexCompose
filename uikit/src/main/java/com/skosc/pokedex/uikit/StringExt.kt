package com.skosc.pokedex.uikit

import androidx.compose.ui.text.capitalize
import java.util.*

fun String.titlecase(): String =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }