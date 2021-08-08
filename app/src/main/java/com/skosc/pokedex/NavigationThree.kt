package com.skosc.pokedex

import com.skosc.pokedex.feature.itemlist.ItemListDestination
import com.skosc.pokedex.feature.itemlist.MoveListDestination
import com.skosc.pokedex.feature.newslist.NewsListDestination
import com.skosc.pokedex.feature.pokemonlist.PokemonListDestination
import com.skosc.pokedex.navigation.root.RootDestination

val root = RootDestination

val RootDestination.pokemonList get() = PokemonListDestination

val RootDestination.moveList get() = MoveListDestination

val RootDestination.newsList get() = NewsListDestination

val RootDestination.itemList get() = ItemListDestination