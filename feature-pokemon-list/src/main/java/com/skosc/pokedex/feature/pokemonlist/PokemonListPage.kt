package com.skosc.pokedex.feature.pokemonlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.navigation.root.RootDestination
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.widget.PokemonCard
import com.skosc.pokedex.uikit.widget.RootLayout
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.widget.PairTileLayout

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.PokemonListPage() = composable(PokemonListDestination.path) {
    val viewModel = diViewModel<PokemonListViewModel>()
    val items: LazyPagingItems<Pokemon> = viewModel.pokemon.collectAsLazyPagingItems()

    RootLayout(header = "All Pokémon") {
        item { Spacer(modifier = Modifier.size(16.dp)) }

        PairTileLayout(items = items) { idx, pokemon ->
            PokemonCard(
                name = pokemon.name,
                id = pokemon.id,
                types = pokemon.types,
                backgroundColor = PokeColor.fromName(pokemon.color.name),
                imageUrl = pokemon.imageUrl
            )
        }
    }
}