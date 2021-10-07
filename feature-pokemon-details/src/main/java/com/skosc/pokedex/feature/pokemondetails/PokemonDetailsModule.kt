package com.skosc.pokedex.feature.pokemondetails

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.provider

val PokemonDetailsModule = PokeModule("pokemon-details") {
    bind<PokemonDetailsSpecFactory>() with provider { PokemonDetailsSpecFactory(instance()) }
}