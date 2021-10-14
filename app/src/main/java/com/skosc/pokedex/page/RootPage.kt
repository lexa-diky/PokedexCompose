package com.skosc.pokedex.page

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.skosc.pokedex.feature.itemlist.ItemListPage
import com.skosc.pokedex.feature.itemlist.MoveListPage
import com.skosc.pokedex.feature.newslist.NewsListPage
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsPage
import com.skosc.pokedex.navigation.LocalNavController
import com.skosc.pokedex.page.main.MainPage
import com.skosc.pokedex.feature.pokemonlist.PokemonListPage
import com.skosc.pokedex.feature.settings.SettingsPage
import com.skosc.pokedex.feature.typedetails.TypeDetailsPage
import com.skosc.pokedex.feature.typedetails.TypeDetailsPageBottomSheet
import com.skosc.pokedex.rememberLocalAnalyticsSpec
import com.skosc.pokedex.root

@Composable
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
fun RootPage() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    val analytics = rememberLocalAnalyticsSpec()

    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        analytics.onNavigation(destination.route ?: "unknown")
    }

    CompositionLocalProvider(LocalNavController provides navController) {
        ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
            NavHost(navController, root.path) {
                MainPage()
                PokemonListPage()
                NewsListPage()
                MoveListPage()
                ItemListPage()
                PokemonDetailsPage()
                SettingsPage()

                TypeDetailsPageBottomSheet()
            }
        }
    }
}