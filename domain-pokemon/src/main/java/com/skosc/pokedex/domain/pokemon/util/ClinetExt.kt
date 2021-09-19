package com.skosc.pokedex.domain.pokemon.util

import com.skosc.pokedex.core.network.heavyCache
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiLink
import io.ktor.client.*
import io.ktor.client.request.*

suspend inline fun <reified T> HttpClient.get(link: PokeApiLink): T = get(link.url) {
    heavyCache()
}
