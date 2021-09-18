package com.skosc.pokedex.domain.settings.entity

import com.skosc.pokedex.core.localization.Localization

data class PokeAppSettings(
    val localization: Localization = Localization.System
)

fun defaultSettings() = PokeAppSettings()