package com.skosc.pokedex.page.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.enity.ui.NewsBriefingEntry
import com.skosc.pokedex.usecase.LoadNewsBriefing
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsBriefingViewModel(private val loadNews: LoadNewsBriefing) : ViewModel() {

    private val _news: MutableStateFlow<List<NewsBriefingEntry>> = MutableStateFlow(emptyList())
    val news: StateFlow<List<NewsBriefingEntry>> get() = _news

    init {
        viewModelScope.launch {
            _news.emit(loadNews())
        }
    }
}