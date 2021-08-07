package com.skosc.pokedex.feature.newslist.entity.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsApiPayload(
    @SerialName("articles")
    val articles: List<NewsApiNews>
)