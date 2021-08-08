package com.skosc.pokedex.di

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.feature.newslist.NewsListViewModel
import com.skosc.pokedex.page.main.CardBoxViewModel
import com.skosc.pokedex.page.main.NewsBriefingViewModel
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val ViewModelModule = PokeModule("view_model") {
    bind<CardBoxViewModel>() with provider { CardBoxViewModel(instance()) }
    bind<NewsBriefingViewModel>() with provider { NewsBriefingViewModel(instance()) }
    bind<NewsListViewModel>() with provider { NewsListViewModel(instance()) }
}
