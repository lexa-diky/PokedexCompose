package com.skosc.pokedex.domain.pokemon.entity

import java.util.*

data class EvolutionTrigger(
    val names: List<EntityName>
): Named {

    override fun name(locale: Locale): String {
        return names.getForLocale(locale).value
    }
}