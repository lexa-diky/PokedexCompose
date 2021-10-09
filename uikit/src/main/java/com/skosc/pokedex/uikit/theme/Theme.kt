package com.skosc.pokedex.uikit.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = Coloristic(
    background = Color(0xFF000000),
    backgroundAccent = Color(0xFF2E2E2E),
    backgroundShadow = Color(0x19E6E6E6),
    textPrimaryAccent = Color(0xFFFFFFFF),
    textSecondaryAccent = Color(0xFFCACACA),
    textLink = Color(0xFF4286F4),
    accentPrimary = Color(0xFF43DBB0),
    accentSecondary = Color(0xFF03DAC5),
    accentShadow = Color(0x41FFFFFF),
    type = Coloristic.Type(
        normal = Color(0xFF2B3F3C),
        fighting = Color(0xFF193238),
        flying = Color(0xFF193238),
        poison = Color(0xFF4E354E),
        ground = Color(0xFF685037),
        rock = Color(0xFF685037),
        bug = Color(0xFF1F411F),
        ghost = Color(0xFF4E354E),
        steel = Color(0xFF444444),
        fire = Color(0xFF7E4343),
        water = Color(0xFF193238),
        grass = Color(0xFF1F411F),
        electric = Color(0xFF635B28),
        psychic = Color(0xFF7E4343),
        ice = Color(0xFF193238),
        dragon = Color(0xFF193238),
        dark = Color(0xFF444444),
        fairy = Color(0xFF7E4343),
        shadow = Color(0xFF444444),
        unknown = Color(0xFF1C9E84),
    )
)

private val LightColorPalette = Coloristic(
    background = Color(0xFFF7F7F7),
    backgroundAccent = Color(0xFFFFFFFF),
    backgroundShadow = Color(0xF000000),
    textPrimaryAccent = Color(0xFF000000),
    textSecondaryAccent = Color(0xFFFFFFFF),
    textLink = Color(0xFF009AFF),
    accentPrimary = Color(0xFF43DBB0),
    accentSecondary = Color(0xFF03DAC5),
    accentShadow = Color(0x41FFFFFF),
    type = Coloristic.Type(
        normal = Color(0xFF4A6B66),
        fighting = Color(0xFF6AD2EE),
        flying = Color(0xFF6AD2EE),
        poison = Color(0xFFC183C1),
        ground = Color(0xFFCF9F6F),
        rock = Color(0xFFCF9F6F),
        bug = Color(0xFF64D364),
        ghost = Color(0xFFC183C1),
        steel = Color(0xFF444444),
        fire = Color(0xFFEC8484),
        water = Color(0xFF6AD2EE),
        grass = Color(0xFF64D364),
        electric = Color(0xFFFFDE00),
        psychic = Color(0xFFC183C1),
        ice = Color(0xFF6AD2EE),
        dragon = Color(0xFF6AD2EE),
        dark = Color(0xFF444444),
        fairy = Color(0xFFE690A3),
        shadow = Color(0xFF444444),
        unknown = Color(0xFF1C9E84),
    )
)

@Composable
fun PokedexTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val rememberedColors = remember { colors }
    val rippleIndication = rememberRipple()

    CompositionLocalProvider(
        LocalColoristic provides rememberedColors,
        LocalIndication provides rippleIndication,
    ) {
        content()
    }
}