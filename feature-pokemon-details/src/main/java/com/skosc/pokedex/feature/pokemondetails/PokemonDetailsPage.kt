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
import org.kodein.di.compose.LocalDI
import org.kodein.di.instance


@OptIn(ExperimentalPagerApi::class)
fun NavGraphBuilder.PokemonDetailsPage() = composable(PokemonDetailsDestination.path) { navBackStackEntry ->
    val di = LocalDI.current

    var specs by remember { mutableStateOf<List<PokemonDetailsSpec>>(emptyList()) }

    val id = remember { navBackStackEntry.arguments!!.getInt(PokemonDetailsDestination.ARG_ID) }
    val specFactory by di.instance<PokemonDetailsSpecFactory>()

    val settings = LocalSettings.current
    LaunchedEffect(id) {
        specs = specFactory.buildForId(id, settings)
    }

    val state = rememberPagerState(pageCount = specs.size)
    HorizontalPager(state = state, dragEnabled = false) { page ->
        val genericDetailsPageScope = remember { GenericDetailsPageScope(state) }
        genericDetailsPageScope.GenericDetailsPage(specs[page])
    }

}