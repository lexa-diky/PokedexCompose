package com.skosc.pokedex.uikit.widget.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.skosc.pokedex.uikit.widget.PokeText

@Composable
fun TagContainerScope.ToggleFilterTag(text: String, modifier: Modifier = Modifier, isToggledDefault: Boolean = false, onClick: (Boolean) -> Unit = {}) {
    var isToggled by remember { mutableStateOf(isToggledDefault) }

    PokeText(
        text = text,
        secondary = false,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    if (isToggled) {
                        toggleBackgroundColorDefault()
                    } else {
                        toggleBackgroundColor()
                    }
                    isToggled = !isToggled
                }
            )
            .then(modifier)
    )
}