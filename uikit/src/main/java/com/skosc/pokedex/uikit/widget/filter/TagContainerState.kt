package com.skosc.pokedex.uikit.widget.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.skosc.pokedex.uikit.theme.Coloristic
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.theme.PokeColor


data class TagContainerState(val toggled: Boolean = false) {

}

@Composable
fun rememberTagContainerState(): MutableState<TagContainerState> = remember {
    mutableStateOf(TagContainerState(false))
}
