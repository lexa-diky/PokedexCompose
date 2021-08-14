package com.skosc.pokedex.feature.pokemondetails

import com.skosc.pokedex.core.di.PokeModule
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val PokemonDetailsModule = PokeModule("pokemon-details") {
    bind<PokemonDetailsSpec>() with factory { order: Int -> PokemonDetailsSpec(instance(), order) }
}