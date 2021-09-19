package com.skosc.pokedex.core.localization

import java.util.*

enum class Localization(val locale: () -> Locale) {
    System(locale = { Locale.getDefault() }),
    English(locale = { Locale.ENGLISH }),
    Russian(locale = { Locale("ru") });

    companion object {

        val nameCache = values().associateBy { it.locale().displayLanguage }
    }
}
