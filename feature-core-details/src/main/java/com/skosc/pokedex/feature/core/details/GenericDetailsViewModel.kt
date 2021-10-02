package com.skosc.pokedex.feature.core.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.feature.core.details.entity.BaseDetailsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GenericDetailsViewModel<T>(private val spec: GenericDetailsSpec<T>) : ViewModel() {

    private val _details: MutableStateFlow<BaseDetailsItem?> = MutableStateFlow(null)
    val details: StateFlow<BaseDetailsItem?> = _details

    init {
        viewModelScope.launch {
            val model = spec.source()

            _details.value = BaseDetailsItem(
                header = spec.headerMapper(model),
                pages = spec.pagesMapper(model),
                background = spec.backgroundMapper(model)
            )
        }
    }
}