package com.skosc.pokedex.domain.pokemon.entity

import com.skosc.pokedex.core.localization.LocalizableResource
import com.skosc.pokedex.core.localization.Localization
import java.util.*

class PokemonAbility(
    val name: String,
    val names: List<EntityName>,
    val isHidden: Boolean,
    val currentEffect: PokemonAbilityEntries,
    val link: PokemonAbilityLink
): Named, LocalizableResource {

    override fun name(locale: Locale): String {
        return names.getForLocale(locale).value
    }

    override fun getLocalized(localization: Localization): String {
        return name(localization.locale())
    }
}
