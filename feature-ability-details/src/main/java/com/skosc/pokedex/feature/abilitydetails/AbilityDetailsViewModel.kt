package com.skosc.pokedex.feature.abilitydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.domain.pokemon.entity.PokemonAbility
import com.skosc.pokedex.domain.pokemon.entity.PokemonAbilityLink
import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AbilityDetailsViewModel(private val repository: PokemonRepository, private val link: PokemonAbilityLink) : ViewModel() {

    val state: MutableStateFlow<PokemonAbility?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            state.value = repository.getAbility(link)
        }
    }
}