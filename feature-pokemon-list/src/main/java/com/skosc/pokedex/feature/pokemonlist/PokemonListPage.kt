package com.skosc.pokedex.feature.pokemonlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.uikit.theme.PokeColor

fun NavGraphBuilder.PokemonListPage() = composable(PokemonListDestination.path) {
    GenericItemListPage<Pokemon>("All Pokémon") {
        BaseListItem(
            it.id,
            it.name,
            it.types,
            it.imageUrl,
            PokeColor.fromName(it.color.name)
        )
    }
}