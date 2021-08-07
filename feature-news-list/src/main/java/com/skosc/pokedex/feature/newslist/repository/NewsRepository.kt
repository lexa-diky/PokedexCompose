package com.skosc.pokedex.feature.newslist.repository

import com.skosc.pokedex.feature.newslist.service.PokemonNewsService
import com.skosc.pokedex.feature.newslist.entity.NewsArticle


class NewsRepository(private val newsService: PokemonNewsService) {

    suspend fun getBriefing(): List<NewsArticle> {
        return getNews()
            .take(BRIEFING_SIZE)
    }

    suspend fun getNews(): List<NewsArticle> {
        return newsService.loadNews().articles
            .filter { !it.image.isNullOrBlank() }
            .map {
                NewsArticle(
                    title = it.title,
                    time = it.time,
                    image = it.image.orEmpty(),
                    url = it.url
                )
            }

    }

    companion object {

        private const val BRIEFING_SIZE = 6
    }
}