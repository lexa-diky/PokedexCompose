package com.skosc.pokedex.di

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.feature.movelist.MoveListViewModel
import com.skosc.pokedex.feature.newslist.NewsListViewModel
import com.skosc.pokedex.page.main.CardBoxViewModel
import com.skosc.pokedex.page.main.NewsBriefingViewModel
import com.skosc.pokedex.feature.pokemonlist.PokemonListViewModel
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val ViewModelModule = PokeModule("view_model") {
    bind<CardBoxViewModel>() with provider { CardBoxViewModel(instance()) }
    bind<NewsBriefingViewModel>() with provider { NewsBriefingViewModel(instance()) }
    bind<PokemonListViewModel>() with provider { PokemonListViewModel(instance()) }
    bind<NewsListViewModel>() with provider { NewsListViewModel(instance()) }
    bind<MoveListViewModel>() with provider { MoveListViewModel(instance()) }
}
