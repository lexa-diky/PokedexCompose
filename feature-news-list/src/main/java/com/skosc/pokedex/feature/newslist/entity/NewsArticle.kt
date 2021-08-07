package com.skosc.pokedex.feature.newslist.entity

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class NewsArticle(
    val title: String,
    val time: Instant,
    val image: String,
    val url: String
)