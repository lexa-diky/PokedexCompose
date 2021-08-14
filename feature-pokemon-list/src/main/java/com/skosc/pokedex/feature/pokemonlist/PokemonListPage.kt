package com.skosc.pokedex.feature.pokemonlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsDestination
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsPage
import com.skosc.pokedex.navigation.LocalNavController
import com.skosc.pokedex.navigation.navigate
import com.skosc.pokedex.uikit.theme.PokeColor

fun NavGraphBuilder.PokemonListPage() = composable(PokemonListDestination.path) {
    val navController = LocalNavController.current

    GenericItemListPage<Pokemon>(
        header = "All Pokémon",
        onItemSelected = { order ->
            navController.navigate(PokemonDetailsDestination, mapOf(
                PokemonDetailsDestination.ARG_ORDER to order
            ))
        },
        mapper = {
            BaseListItem(
                it.id,
                it.name,
                it.types,
                it.imageUrl,
                PokeColor.fromName(it.color.name)
            )
        }
    )
}