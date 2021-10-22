package com.skosc.pokedex.uikit.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author a.l.yakovlev
 */
object ScreenDimensionTools {
    @Composable
    fun screenHeight(): Dp {
        val config = LocalConfiguration.current
        return config.screenHeightDp.dp
    }
}