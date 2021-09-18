package com.skosc.pokedex.domain.settings

import androidx.compose.runtime.compositionLocalOf
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings

val LocalSettings = compositionLocalOf<PokeAppSettings> { error("No local settings provided") }
