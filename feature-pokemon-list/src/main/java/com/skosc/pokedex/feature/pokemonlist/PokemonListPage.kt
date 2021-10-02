package com.skosc.pokedex.feature.pokemonlist

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.util.getLocalized
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.feature.core.list.ListFilter
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsDestination
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsPage
import com.skosc.pokedex.navigation.LocalNavController
import com.skosc.pokedex.navigation.navigate
import com.skosc.pokedex.uikit.coloristics.Coloristic
import com.skosc.pokedex.uikit.theme.PokemonColor

fun NavGraphBuilder.PokemonListPage() = composable(PokemonListDestination.path) {
    val navController = LocalNavController.current
    val settings = LocalSettings.current

    GenericItemListPage<PokemonSpecies>(
        header = "All Pokémon",
        onItemSelected = { order ->
            navController.navigate(
                PokemonDetailsDestination, mapOf(
                    PokemonDetailsDestination.ARG_ORDER to order
                )
            )
        },
        mapper = { species ->
            val leftColor = getLeftColor(species)
            BaseListItem(
                order = species.id,
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

private fun getLeftColor(species: PokemonSpecies): Color {
    return Coloristic.getPokeColorForType(species.defaultVariety.types.first().defaultName)
}

private fun getRightColor(species: PokemonSpecies, leftColor: Color): Color {
    val second = species.defaultVariety.types.getOrNull(1)
    return if (second != null) {
        Coloristic.getPokeColorForType(second.defaultName)
    } else {
        leftColor
    }
}
