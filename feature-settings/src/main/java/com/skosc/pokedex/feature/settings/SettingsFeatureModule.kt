package com.skosc.pokedex.feature.settings

import com.skosc.pokedex.core.di.PokeModule
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val SettingsFeatureModule = PokeModule("settings-feature") {
    bind<LanguageSettingsViewModel>() with provider { LanguageSettingsViewModel(instance()) }
}