package com.skosc.pokedex.domain.settings.utils

import androidx.compose.runtime.Composable
import com.skosc.pokedex.core.localization.LocalizableResource
import com.skosc.pokedex.domain.settings.LocalSettings

val LocalizableResource.localized: String  @Composable get() {
    return getLocalized(LocalSettings.current.localization)
}