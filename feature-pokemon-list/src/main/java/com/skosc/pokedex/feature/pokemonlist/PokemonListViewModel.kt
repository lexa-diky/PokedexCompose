package com.skosc.pokedex.feature.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.paging.PokemonDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonListViewModel(private val source: PokemonDataSource) : ViewModel() {

    private val _pokemon: MutableStateFlow<PagingData<Pokemon>> = MutableStateFlow(PagingData.empty())
    val pokemon: StateFlow<PagingData<Pokemon>> get() = _pokemon

    init {
        viewModelScope.launch {
            createPager().flow.collect {
                _pokemon.emit(it)
            }
        }
    }

    private fun createPager(): Pager<Int, Pokemon> = Pager(
        pagingSourceFactory = { source },
        config = PagingConfig(5)
    )
}