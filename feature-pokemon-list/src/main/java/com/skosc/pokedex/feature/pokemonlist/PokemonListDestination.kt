package com.skosc.pokedex.feature.pokemonlist

import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.navigation.ParcelableVoid

object PokemonListDestination : Destination<ParcelableVoid> {
    override val path: String = "/pokemon-list"
    override val arguments: ParcelableVoid = ParcelableVoid
}
