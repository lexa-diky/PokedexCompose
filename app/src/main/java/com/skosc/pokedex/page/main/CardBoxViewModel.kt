package com.skosc.pokedex.page.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.enity.ui.BoxCard
import com.skosc.pokedex.enity.ui.BoxCardList
import com.skosc.pokedex.usecase.CoordinateMenuAndSearch
import com.skosc.pokedex.usecase.LoadBoxCards
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardBoxViewModel(private val coordinateMenuAndSearch: CoordinateMenuAndSearch) : ViewModel() {

    val cards: MutableStateFlow<BoxCardList> = MutableStateFlow(BoxCardList.Menu())

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            val value = coordinateMenuAndSearch(null)
            cards.emit(value)
        }
    }
}
