package com.skosc.pokedex.feature.itemlist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.uikit.widget.PokemonCard
import com.skosc.pokedex.uikit.widget.RootLayout
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.theme.UIColor
import com.skosc.pokedex.uikit.widget.PairTileLayout

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.MoveListPage() = composable(MoveListDestination.path) {
    val settings = LocalSettings.current
    GenericItemListPage<PokemonMove>("Moves") {
        BaseListItem(
            it.id,
            it.name,
            listOf(it.type.getLocalized(settings.localization)),
            "",
            UIColor.Accent.Red
        )
    }
}