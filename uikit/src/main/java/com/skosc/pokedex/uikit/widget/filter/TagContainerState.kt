package com.skosc.pokedex.uikit.widget.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


data class TagContainerState(val toggled: Boolean = false) {

}

@Composable
fun rememberTagContainerState(): MutableState<TagContainerState> = remember {
    mutableStateOf(TagContainerState(false))
}
