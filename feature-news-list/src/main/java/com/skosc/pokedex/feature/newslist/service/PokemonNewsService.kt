package com.skosc.pokedex.feature.newslist.service

import com.skosc.pokedex.core.network.ApiKeys
import com.skosc.pokedex.feature.newslist.entity.network.NewsApiPayload
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class PokemonNewsService(private val client: HttpClient) {

    suspend fun loadNews(): NewsApiPayload {
        return client.request("https://newsapi.org/v2/everything") {
            method = HttpMethod.Get
            parameter("q", POKEMON_QUERY)
            parameter("apiKey", ApiKeys.API_KEY)
        }
    }

    companion object {

        private const val POKEMON_QUERY = "pokemon"
    }
}