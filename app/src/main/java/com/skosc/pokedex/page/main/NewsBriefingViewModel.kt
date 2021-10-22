package com.skosc.pokedex.page.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.enity.ui.NewsBriefingEntry
import com.skosc.pokedex.usecase.LoadNewsBriefing
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsBriefingViewModel(private val loadNews: LoadNewsBriefing) : ViewModel() {

    val news: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Loading)

    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        news.value = NewsState.Error
    }

    init {
        viewModelScope.launch(errorHandler) {
            news.value = NewsState.Loaded(loadNews())
        }
    }
}