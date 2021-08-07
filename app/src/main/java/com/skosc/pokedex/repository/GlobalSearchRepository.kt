package com.skosc.pokedex.repository

import com.skosc.pokedex.enity.domain.SearchResult
import com.skosc.pokedex.enity.domain.SearchResultIcon

class GlobalSearchRepository {

    suspend fun search(query: String): List<SearchResult> {
        return listOf(
            SearchResult("Pikachu", SearchResultIcon.Pokemon(PLACEHOLDER_POKEMON_IMAGE)),
            SearchResult("Mewut", SearchResultIcon.Pokemon(PLACEHOLDER_POKEMON_IMAGE)),
            SearchResult("Hyper Potion", SearchResultIcon.Pokemon(PLACEHOLDER_POKEMON_IMAGE)),
        )
    }

    companion object {

        private const val PLACEHOLDER_POKEMON_IMAGE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
    }
}