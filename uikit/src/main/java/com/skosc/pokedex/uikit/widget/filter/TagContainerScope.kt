package com.skosc.pokedex.uikit.widget.filter

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

interface TagContainerScope {

    fun toggleBackgroundColor()

    fun toggleBackgroundColorDefault()

    companion object {

        internal operator fun invoke(state: MutableState<TagContainerState>): TagContainerScope = TagContainerScopeImpl(state)
    }
}

internal class TagContainerScopeImpl(private val state: MutableState<TagContainerState>) : TagContainerScope {

    override fun toggleBackgroundColor() {
        state.value = state.value.copy(toggled = true)
    }

    override fun toggleBackgroundColorDefault() {
        state.value = state.value.copy(toggled = false)
    }
}