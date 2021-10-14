package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.skosc.pokedex.uikit.theme.LocalColoristic

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokeDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    val coloristic = LocalColoristic.current

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Surface(
            elevation = 8.dp,
            color = coloristic.background,
        ) {
            content()
        }
    }
}