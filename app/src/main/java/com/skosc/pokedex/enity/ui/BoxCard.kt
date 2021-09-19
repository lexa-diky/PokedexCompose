package com.skosc.pokedex.enity.ui

import androidx.compose.ui.graphics.Color
import com.skosc.pokedex.enity.domain.SearchResultIcon
import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.pokemonList
import com.skosc.pokedex.root
import com.skosc.pokedex.uikit.theme.PokemonColor

sealed class BoxCard {

    data class Menu(
        val title: String,
        val color: Color,
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
    BoxCard.Menu("Pokemon", PokemonColor.random, root.pokemonList),
    BoxCard.Menu("Items", PokemonColor.random, root.pokemonList),
    BoxCard.Menu("Moves", PokemonColor.random, root.pokemonList),
    BoxCard.Menu("Abilities", PokemonColor.random, root.pokemonList),
    BoxCard.Menu("Locations", PokemonColor.random, root.pokemonList)
)