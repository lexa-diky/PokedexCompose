package com.skosc.pokedex.feature.typedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.domain.pokemon.entity.PokemonType
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected
import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TypeDetailsBottomSheetViewModel(private val repository: PokemonRepository, init: TypeDetailsInit) : ViewModel() {

    val typeInfo: MutableStateFlow<TypeDetailsState?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            typeInfo.value = TypeDetailsState(
                primaryType = repository.getType(init.primaryType),
                secondaryType = init.secondaryType?.let { repository.getType(it) },
                isEditable = init.isEditable,
                allTypes = repository.allTypes(),
                showTypeSelection = false
            )
        }
    }

    fun onEvent(event: TypeDetailsEvent) {
        when(event) {
            is TypeDetailsEvent.ShowTypeSelectDialog -> {
                typeInfo.value = typeInfo.value?.copy(
                    showTypeSelection = true
                )
            }
            is TypeDetailsEvent.TypesSelected -> {
                viewModelScope.launch {
                    typeInfo.value = typeInfo.value?.copy(
                        primaryType = repository.getType(event.types.first),
                        secondaryType = event.types.second?.let { repository.getType(it) }
                    )
                }
            }
            is TypeDetailsEvent.DismissSelection -> {
                typeInfo.value = typeInfo.value?.copy(
                    showTypeSelection = false
                )
            }
        }
    }
}