package com.skosc.pokedex.usecase

import com.skosc.pokedex.enity.ui.BoxCard
import com.skosc.pokedex.enity.ui.BoxCardList
import com.skosc.pokedex.repository.GlobalSearchRepository

class PerformGlobalSearch(private val searchRepository: GlobalSearchRepository) {

    suspend operator fun invoke(query: String): BoxCardList.SearchResult {
        return searchRepository.search(query)
            .map { BoxCard.SearchResult(it.title, it.icon) }
            .let( BoxCardList::SearchResult)
    }
}