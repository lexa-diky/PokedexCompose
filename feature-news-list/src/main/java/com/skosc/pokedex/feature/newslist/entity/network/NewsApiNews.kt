package com.skosc.pokedex.feature.newslist.entity.network

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsApiNews(
    @SerialName("title")
    val title: String,
    @SerialName("publishedAt")
    val time: Instant,
    @SerialName("urlToImage")
    val image: String?,
    @SerialName("url")
    val url: String
)