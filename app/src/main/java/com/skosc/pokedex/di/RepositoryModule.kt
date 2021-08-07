package com.skosc.pokedex.di

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.repository.GlobalSearchRepository
import com.skosc.pokedex.repository.MenuRepository
import com.skosc.pokedex.feature.newslist.repository.NewsRepository
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val RepositoryModule = PokeModule("repo") {
    bind<MenuRepository>() with singleton { MenuRepository() }
    bind<NewsRepository>() with singleton { NewsRepository(instance()) }
    bind<GlobalSearchRepository>() with singleton { GlobalSearchRepository() }
}
