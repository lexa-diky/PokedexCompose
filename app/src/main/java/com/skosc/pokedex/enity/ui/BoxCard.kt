package com.skosc.pokedex.enity.ui

import com.skosc.pokedex.core.localization.text.Text
import com.skosc.pokedex.enity.domain.SearchResultIcon
import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.pokemonList
import com.skosc.pokedex.root
import com.skosc.pokedex.uikit.theme.ColorDef

sealed class BoxCard {

    data class Menu(
        val title: Text,
        val color: ColorDef,
        val destination: Destination
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
    BoxCard.Menu(Text("Pokemon"), ColorDef.TypeElectric, root.pokemonList),
    BoxCard.Menu(Text("Items"), ColorDef.TypeElectric, root.pokemonList),
    BoxCard.Menu(Text("Moves"), ColorDef.TypeElectric, root.pokemonList),
    BoxCard.Menu(Text("Abilities"), ColorDef.TypeElectric, root.pokemonList),
    BoxCard.Menu(Text("Locations"), ColorDef.TypeElectric, root.pokemonList)
)