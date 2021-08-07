package com.skosc.pokedex.feature.newslist.usecase

import com.skosc.pokedex.feature.newslist.repository.NewsRepository
import com.skosc.pokedex.feature.newslist.entity.NewsArticle

class LoadNews(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(): List<NewsArticle> {
        return newsRepository.getNews()
    }
}