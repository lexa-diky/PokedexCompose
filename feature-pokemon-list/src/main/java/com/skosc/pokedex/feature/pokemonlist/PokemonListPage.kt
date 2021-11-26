package com.skosc.pokedex.feature.pokemonlist

import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.util.getLocalized
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.feature.core.list.ListFilter
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsPage
import com.skosc.pokedex.navigation.LocalNavigator
import com.skosc.pokedex.navigation.RouteComposable
import com.skosc.pokedex.uikit.coloristics.ColorPicker
import com.skosc.pokedex.uikit.theme.ColorDef

val PokemonListPage = RouteComposable("/pokemon-list") {
    val navigator = LocalNavigator.current
    val settings = LocalSettings.current

    GenericItemListPage(
        header = "All Pokémon",
        onItemSelected = { id -> navigator.navigate(PokemonDetailsPage, id) },
        mapper = { species ->
            val leftColor = getLeftColor(species)
            BaseListItem(
                id = species.id,
                name = species.defaultVariety.name,
                tags = species.defaultVariety.types.map { type -> type.names.getLocalized(settings.localization) },
                image = species.defaultVariety.imageUrl,
                leftColor = leftColor,
                rightColor = getRightColor(species, leftColor)
            )
        },
        filters = listOf(
            ListFilter.Field<PokemonSpecies>("Name") { true }
        )
    )
}

private fun getLeftColor(species: PokemonSpecies): ColorDef {
    return ColorPicker.getPokeColorForType(species.defaultVariety.types.first().defaultName)
}

private fun getRightColor(species: PokemonSpecies, leftColor: ColorDef): ColorDef {
    val second = species.defaultVariety.types.getOrNull(1)
    return if (second != null) {
        ColorPicker.getPokeColorForType(second.defaultName)
    } else {
        leftColor
    }
}
