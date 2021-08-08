package com.skosc.pokedex.feature.itemlist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.widget.PokemonCard
import com.skosc.pokedex.uikit.widget.RootLayout
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.widget.PairTileLayout

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.ItemListPage() = composable(ItemListDestination.path) {
    GenericItemListPage<PokemonItem>("Items") {
        BaseListItem(
            it.id,
            it.name,
            emptyList(),
            it.sprite,
            PokeColor.Red
        )
    }
}