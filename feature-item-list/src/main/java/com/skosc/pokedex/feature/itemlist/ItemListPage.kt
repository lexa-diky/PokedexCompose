package com.skosc.pokedex.feature.itemlist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.core.list.BaseListItem
import com.skosc.pokedex.feature.core.list.GenericItemListPage
import com.skosc.pokedex.navigation.RouteComposable
import com.skosc.pokedex.uikit.coloristics.ColorPicker

val ItemListPage = RouteComposable("/item-list") {
    val context = LocalContext.current
    val settings = LocalSettings.current
    GenericItemListPage<PokemonItem>("Items") {
        BaseListItem(
            it.id,
            it.name(settings.localization.locale()),
            emptyList(),
            it.sprite,
            ColorPicker.getPokeColor(context, it.sprite)
        )
    }
}