package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.runtime.*
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.core.details.GenericDetailsPage
import com.skosc.pokedex.feature.core.details.GenericDetailsPageScope
import com.skosc.pokedex.navigation.RouteComposable
import org.kodein.di.compose.LocalDI
import org.kodein.di.instance


@OptIn(ExperimentalPagerApi::class)
val PokemonDetailsPage = RouteComposable("/pokemon/details") { id: Int ->
    val di = LocalDI.current

    var specs by remember { mutableStateOf<List<PokemonDetailsSpec>>(emptyList()) }

    val specFactory by di.instance<PokemonDetailsSpecFactory>()

    val settings = LocalSettings.current
    LaunchedEffect(id) {
        specs = specFactory.buildForId(id, settings)
    }

    val state = rememberPagerState(initialPage = 0)
    HorizontalPager(count = specs.size, state = state) { page ->
        val genericDetailsPageScope = remember { GenericDetailsPageScope(state) }
        genericDetailsPageScope.GenericDetailsPage(specs[page])
    }
}