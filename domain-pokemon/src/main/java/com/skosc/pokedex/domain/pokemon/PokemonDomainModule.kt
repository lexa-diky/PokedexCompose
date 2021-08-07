package com.skosc.pokedex.domain.pokemon

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.domain.pokemon.paging.PokemonDataSource
import com.skosc.pokedex.domain.pokemon.paging.PokemonMoveDataSource
import com.skosc.pokedex.domain.pokemon.service.PokeApiService
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val PokemonDomainModule = PokeModule("domain_pokemon") {
    bind<PokeApiService>() with singleton { PokeApiService(instance()) }
    bind<PokemonDataSource>() with singleton { PokemonDataSource(instance()) }
    bind<PokemonMoveDataSource>() with singleton { PokemonMoveDataSource(instance()) }
}