package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings
import com.skosc.pokedex.feature.core.details.GenericDetailsPage
import com.skosc.pokedex.feature.core.details.GenericDetailsSpec
import com.skosc.pokedex.uikit.diViewModel
import org.kodein.di.compose.LocalDI
import org.kodein.di.compose.localDI
import org.kodein.di.instance


fun NavGraphBuilder.PokemonDetailsPage() = composable(PokemonDetailsDestination.path) { navBackStackEntry ->
    val di = LocalDI.current
    val settings = LocalSettings.current

    val order = remember { navBackStackEntry.arguments!!.getInt(PokemonDetailsDestination.ARG_ORDER) }
    val spec by di.instance<Pair<Int, PokeAppSettings>, PokemonDetailsSpec>(arg = order to settings)

    GenericDetailsPage(spec)
}