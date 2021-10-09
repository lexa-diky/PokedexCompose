package com.skosc.pokedex.uikit.widget.filter

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.theme.PokeShapes

@Composable
fun FilterTagContainer(
    modifier: Modifier = Modifier,
    state: MutableState<TagContainerState> = rememberTagContainerState(),
    content: @Composable TagContainerScope.() -> Unit
) {
    val scope = TagContainerScope(state)

    val coloristic = LocalColoristic.current
    val background = if (state.value.toggled) coloristic.accentPrimary else  coloristic.backgroundShadow

    Box(
        modifier = Modifier
            .background(background, PokeShapes.ChipShape)
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .animateContentSize()
            .then(modifier)
    ) {
        scope.content()
    }
}