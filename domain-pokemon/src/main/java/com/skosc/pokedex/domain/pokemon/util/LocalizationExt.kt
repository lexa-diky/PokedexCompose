package com.skosc.pokedex.domain.pokemon.util

import com.skosc.pokedex.core.localization.Localization
import com.skosc.pokedex.domain.pokemon.entity.EntityName

fun List<EntityName>.getLocalized(locale: Localization): String {
    return firstOrNull { it.language == locale.locale().language }?.value
        ?: firstOrNull { it.language == Localization.English.locale().language }?.value
        ?: first().value
}