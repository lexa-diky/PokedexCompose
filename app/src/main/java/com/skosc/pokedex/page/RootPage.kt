package com.skosc.pokedex.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.skosc.pokedex.feature.itemlist.ItemListPage
import com.skosc.pokedex.feature.itemlist.MoveListPage
import com.skosc.pokedex.feature.newslist.NewsListPage
import com.skosc.pokedex.navigation.LocalNavController
import com.skosc.pokedex.page.main.MainPage
import com.skosc.pokedex.feature.pokemonlist.PokemonListPage
import com.skosc.pokedex.root

@Composable
fun RootPage() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController, root.path) {
            MainPage()
            PokemonListPage()
            NewsListPage()
            MoveListPage()
            ItemListPage()
        }
    }
}