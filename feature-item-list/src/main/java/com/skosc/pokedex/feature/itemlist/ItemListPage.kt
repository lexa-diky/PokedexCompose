package com.skosc.pokedex.feature.itemlist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.uikit.coloristics.Coloristic
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.widget.PokemonCard
import com.skosc.pokedex.uikit.widget.RootLayout
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.widget.PairTileLayout
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.ItemListPage() = composable(ItemListDestination.path) {
    val context = LocalContext.current
    val settings = LocalSettings.current
    GenericItemListPage<PokemonItem>("Items") {
        BaseListItem(
            it.id,
            it.name(settings.localization.locale()),
            emptyList(),
            it.sprite,
            Coloristic.getPokeColor(context, it.sprite)
        )
    }
}