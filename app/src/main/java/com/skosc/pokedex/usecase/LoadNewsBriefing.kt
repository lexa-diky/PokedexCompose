package com.skosc.pokedex.usecase

import com.skosc.pokedex.enity.ui.NewsBriefingEntry
import com.skosc.pokedex.feature.newslist.repository.NewsRepository

class LoadNewsBriefing(private val repository: NewsRepository) {

    suspend operator fun invoke(): List<NewsBriefingEntry> {
        return repository.getBriefing().map {
            NewsBriefingEntry(title = it.title, time = it.time, image = it.image, url = it.url)
        }
    }
}
