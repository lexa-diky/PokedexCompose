package com.skosc.pokedex.uikit.widget.filter

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

interface TagContainerScope {

    fun toggleBackgroundColor(color: Color)

    fun toggleBackgroundColorDefault()

    companion object {

        internal operator fun invoke(state: MutableState<TagContainerState>): TagContainerScope = TagContainerScopeImpl(state)
    }
}

internal class TagContainerScopeImpl(private val state: MutableState<TagContainerState>) : TagContainerScope {

    override fun toggleBackgroundColor(color: Color) {
        Log.i("ATHERE", "toggle on")
        state.value = state.value.copy(backgroundColor = color)
    }

    override fun toggleBackgroundColorDefault() {
        Log.i("ATHERE", "toggle off")
        state.value = state.value.copy(backgroundColor = TagContainerState.DEFAULT.backgroundColor)
    }
}