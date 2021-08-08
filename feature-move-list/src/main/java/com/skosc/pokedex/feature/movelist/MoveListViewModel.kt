package com.skosc.pokedex.feature.movelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.pokemon.paging.PokemonDataSource
import com.skosc.pokedex.domain.pokemon.paging.PokemonMoveDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoveListViewModel(private val source: PokemonMoveDataSource) : ViewModel() {

    private val _moves: MutableStateFlow<PagingData<PokemonMove>> = MutableStateFlow(PagingData.empty())
    val moves: StateFlow<PagingData<PokemonMove>> get() = _moves

    init {
        viewModelScope.launch {
            createPager().flow.collect {
                _moves.emit(it)
            }
        }
    }

    private fun createPager(): Pager<Int, PokemonMove> = Pager(
        pagingSourceFactory = { source },
        config = PagingConfig(15)
    )
}