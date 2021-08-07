package com.skosc.pokedex.di

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.feature.newslist.service.PokemonNewsService

import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton


val ServiceModule = PokeModule("service") {
    bind<PokemonNewsService>() with singleton { PokemonNewsService(instance())  }
}