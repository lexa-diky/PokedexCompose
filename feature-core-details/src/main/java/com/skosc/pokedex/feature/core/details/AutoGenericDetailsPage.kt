package com.skosc.pokedex.feature.core.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.skosc.pokedex.uikit.localViewModel

@Composable
fun <T> GenericDetailsPage(spec: GenericDetailsSpec<T>) {
    val viewModel = localViewModel { GenericDetailsViewModel(spec) }

    val details by viewModel.details.collectAsState()
    if (details != null) {
        GenericDetailsPage(details = details!!)
    }
}
