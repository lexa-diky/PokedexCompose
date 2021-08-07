package com.skosc.pokedex.enity.ui

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class NewsBriefingEntry(
    val title: String,
    val time: Instant,
    val image: String,
    val url: String
) {

    companion object
}

val NewsBriefingEntry.Companion.sample get() = listOf(
    NewsBriefingEntry(
        title = "Pokemon Unite! Released",
        time = Clock.System.now(),
        image = "https://img.youtube.com/vi/2sj2iQyBTQs/maxresdefault.jpg",
        url = ""
    ),
    NewsBriefingEntry(
        title = "Pokemon Unite! Released",
        time = Clock.System.now(),
        image = "https://assets.pokemon.com/assets//cms2-ru-ru/img/video-games/_tiles/pokemon-unite/launch/pokemon-unite-875.jpg",
        url = ""
    ),
    NewsBriefingEntry(
        title = "Pokemon Unite! Released",
        time = Clock.System.now(),
        image = "https://assets.pokemon.com/assets//cms2-ru-ru/img/video-games/_tiles/pokemon-unite/launch/pokemon-unite-875.jpg",
        url = ""
    ),
    NewsBriefingEntry(
        title = "Pokemon Unite! Released",
        time = Clock.System.now(),
        image = "https://assets.pokemon.com/assets//cms2-ru-ru/img/video-games/_tiles/pokemon-unite/launch/pokemon-unite-875.jpg",
        url = ""
    )
)

