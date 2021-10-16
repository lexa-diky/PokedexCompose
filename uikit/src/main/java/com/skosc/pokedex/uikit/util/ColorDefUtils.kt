package com.skosc.pokedex.uikit.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.skosc.pokedex.uikit.theme.ColorDef
import com.skosc.pokedex.uikit.theme.LocalColoristic

val ColorDef.local: Color @Composable get() = LocalColoristic.current.from(this)