package com.skosc.pokedex.uikit.widget.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.skosc.pokedex.uikit.theme.PokeColor

data class TagContainerState(val backgroundColor: androidx.compose.ui.graphics.Color = PokeColor.ShadowGray) {

    companion object {

        internal val DEFAULT = TagContainerState()
    }
}

@Composable
fun rememberTagContainerState(): MutableState<TagContainerState> = remember { mutableStateOf(TagContainerState()) }
