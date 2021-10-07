package com.skosc.pokedex.feature.pokemondetails

import com.skosc.pokedex.navigation.Destination

object PokemonDetailsDestination : Destination {

    override val path: String = "pokemon/details/"

    const val ARG_ID = "order"
}