package com.skosc.pokedex.feature.movelist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.widget.PokemonCard
import com.skosc.pokedex.uikit.widget.RootLayout
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.widget.PairTileLayout

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.MoveListPage() = composable(MoveListDestination.path) {
    val viewModel = diViewModel<MoveListViewModel>()
    val items: LazyPagingItems<PokemonMove> = viewModel.moves.collectAsLazyPagingItems()

    RootLayout(header = "All Pokémon") {
        item { Spacer(modifier = Modifier.size(16.dp)) }

        PairTileLayout(items = items) { idx, move ->
            PokemonCard(
                name = move.name,
                id = move.id,
                types = listOf(move.type),
                backgroundColor = PokeColor.fromName(move.type),
                imageUrl = ""
            )
        }
    }
}