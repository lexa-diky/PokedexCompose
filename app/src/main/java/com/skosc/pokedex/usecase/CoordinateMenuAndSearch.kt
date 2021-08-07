package com.skosc.pokedex.usecase

import com.skosc.pokedex.enity.ui.BoxCardList
import java.lang.ref.SoftReference

class CoordinateMenuAndSearch(
    private val loadBoxCards: LoadBoxCards,
    private val performGlobalSearch: PerformGlobalSearch
) {

    private var softBoxCards: SoftReference<BoxCardList.Menu>? = null

    suspend operator fun invoke(query: String?): BoxCardList {
        return if (query.isNullOrBlank()) {
            loadBoxCardsCached()
        } else {
            performGlobalSearch(query)
        }
    }

    @Synchronized
    private suspend fun loadBoxCardsCached(): BoxCardList.Menu {
        return softBoxCards?.let { it.get() }
            ?: run {
               val newLoad = loadBoxCards()
               softBoxCards = SoftReference(newLoad)
               newLoad
            }
    }
}