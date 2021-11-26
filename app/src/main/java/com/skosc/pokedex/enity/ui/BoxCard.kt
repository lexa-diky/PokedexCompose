package com.skosc.pokedex.enity.ui

import com.skosc.pokedex.core.localization.text.Text
import com.skosc.pokedex.enity.domain.SearchResultIcon
import com.skosc.pokedex.feature.pokemonlist.PokemonListPage
import com.skosc.pokedex.navigation.NoArg
import com.skosc.pokedex.navigation.Route
import com.skosc.pokedex.uikit.theme.ColorDef

sealed class BoxCard {

    data class Menu(
        val title: Text,
        val color: ColorDef,
        val destination: Route<NoArg>
    ) : BoxCard() {

        companion object
    }

    data class SearchResult(
        val title: String,
        val icon: SearchResultIcon
    )
}

sealed class BoxCardList {

    data class Menu(private val wrapped: List<BoxCard.Menu> = emptyList()) : BoxCardList(), List<BoxCard.Menu> by wrapped

    data class SearchResult(private val wrapped: List<BoxCard.SearchResult> = emptyList()) : BoxCardList(), List<BoxCard.SearchResult> by wrapped
}

val BoxCard.Menu.Companion.sample get() = listOf(
    BoxCard.Menu(Text("Pokemon"), ColorDef.TypeElectric, PokemonListPage),
    BoxCard.Menu(Text("Items"), ColorDef.TypeElectric, PokemonListPage),
    BoxCard.Menu(Text("Moves"), ColorDef.TypeElectric, PokemonListPage),
    BoxCard.Menu(Text("Abilities"), ColorDef.TypeElectric, PokemonListPage),
    BoxCard.Menu(Text("Locations"), ColorDef.TypeElectric, PokemonListPage)
)