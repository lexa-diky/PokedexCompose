package com.skosc.pokedex.feature.pokemondetails

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val PokemonDetailsModule = PokeModule("pokemon-details") {
    bind<PokemonDetailsSpec>() with factory { params: Pair<Int, PokeAppSettings> -> PokemonDetailsSpec(instance(), params.second, params.first) }
}