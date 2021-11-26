package com.skosc.pokedex.feature.itemlist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.navigation.RouteComposable
import com.skosc.pokedex.uikit.theme.ColorDef

@OptIn(ExperimentalAnimationApi::class)
val MoveListPage = RouteComposable("/move-list") {
    val settings = LocalSettings.current
    GenericItemListPage<PokemonMove>("Moves") {
        BaseListItem(
            it.id,
            it.name,
            listOf(it.type.getLocalized(settings.localization)),
            "",
            ColorDef.AccentPrimary
        )
    }
}
