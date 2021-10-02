package com.skosc.pokedex.uikit.widget.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.skosc.pokedex.uikit.theme.UIColor

@Composable
fun TagContainerScope.ToggleFilterTag(text: String, modifier: Modifier = Modifier, isToggledDefault: Boolean = false, onClick: (Boolean) -> Unit = {}) {
    var isToggled by remember { mutableStateOf(isToggledDefault) }

    Text(
        text = text,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    if (isToggled) {
                        toggleBackgroundColorDefault()
                    } else {
                        toggleBackgroundColor(UIColor.Accent.SoftSwampGreen)
                    }
                    isToggled = !isToggled
                }
            )
            .then(modifier)
    )
}