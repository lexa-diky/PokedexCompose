package com.skosc.pokedex.feature.pokemondetails.battle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokemonDetailsBattlePageViewModel(private val pokemonRepository: PokemonRepository, private val pokemon: Pokemon) : ViewModel() {

    val state: MutableStateFlow<PokemonDetailsBattlePageState?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            val abilities = pokemonRepository.getAbilities(pokemon.abilities)

            state.value = PokemonDetailsBattlePageState(
                abilities = abilities
            )
        }
    }
}