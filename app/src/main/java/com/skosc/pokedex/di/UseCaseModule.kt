package com.skosc.pokedex.di

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.feature.newslist.usecase.LoadNews
import com.skosc.pokedex.usecase.CoordinateMenuAndSearch
import com.skosc.pokedex.usecase.LoadBoxCards
import com.skosc.pokedex.usecase.LoadNewsBriefing
import com.skosc.pokedex.usecase.PerformGlobalSearch
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val UseCaseModule = PokeModule("use_case") {
    bind<LoadBoxCards>() with singleton { LoadBoxCards(instance()) }
    bind<LoadNewsBriefing>() with singleton { LoadNewsBriefing(instance()) }
    bind<PerformGlobalSearch>() with singleton { PerformGlobalSearch(instance()) }
    bind<CoordinateMenuAndSearch>() with singleton { CoordinateMenuAndSearch(instance(), instance()) }
    bind<LoadNews>() with singleton { LoadNews(instance()) }
}
