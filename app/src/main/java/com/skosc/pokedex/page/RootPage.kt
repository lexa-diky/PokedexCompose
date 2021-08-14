package com.skosc.pokedex.page

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.skosc.pokedex.feature.itemlist.ItemListPage
import com.skosc.pokedex.feature.itemlist.MoveListPage
import com.skosc.pokedex.feature.newslist.NewsListPage
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsPage
import com.skosc.pokedex.navigation.LocalNavController
import com.skosc.pokedex.page.main.MainPage
import com.skosc.pokedex.feature.pokemonlist.PokemonListPage
import com.skosc.pokedex.rememberLocalAnalyticsSpec
import com.skosc.pokedex.root

@Composable
fun RootPage() {
    val navController = rememberNavController()
    val analytics = rememberLocalAnalyticsSpec()

    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        analytics.onNavigation(destination.route ?: "unknown")
    }

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController, root.path) {
            MainPage()
            PokemonListPage()
            NewsListPage()
            MoveListPage()
            ItemListPage()
            PokemonDetailsPage()
        }
    }
}