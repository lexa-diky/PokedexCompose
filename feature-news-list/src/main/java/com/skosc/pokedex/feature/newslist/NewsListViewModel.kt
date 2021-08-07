package com.skosc.pokedex.feature.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.feature.newslist.entity.NewsArticle
import com.skosc.pokedex.feature.newslist.usecase.LoadNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsListViewModel(loadNews: LoadNews) : ViewModel() {

    private val _news: MutableStateFlow<List<NewsArticle>> = MutableStateFlow(emptyList())
    val news: Flow<List<NewsArticle>> get() = _news

    init {
        viewModelScope.launch {
            _news.emit(loadNews())
        }
    }
}