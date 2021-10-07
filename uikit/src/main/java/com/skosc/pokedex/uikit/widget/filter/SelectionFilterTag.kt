package com.skosc.pokedex.uikit.widget.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.widget.ListItemText

data class SelectionTagOption<T>(
    val value: T,
    val title: String,
    val icon: Painter? = null
)

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun <T> TagContainerScope.SelectionFilterTag(
    options: List<SelectionTagOption<T>>,
    modifier: Modifier = Modifier,
    default: T? = null,
    placeholder: String = "",
    onSelected: (T) -> Unit = {}
) {
    var selected: T? by remember { mutableStateOf(default) }
    var isSelectionMode: Boolean by remember { mutableStateOf(false) }

    Text(
        text = options.find { it.value == selected }?.title ?: placeholder,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { isSelectionMode = true }
            )
            .then(modifier)
    )

    if (isSelectionMode) {
        AlertDialog(
            onDismissRequest = { isSelectionMode = false },
            buttons = {
                Button(
                    onClick = {
                        selected = null
                        isSelectionMode = false
                    },
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(text = "Default")
                }
            },
            title = { Text(text = placeholder) },
            text = {
                Column {
                    options.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    selected = it.value
                                    isSelectionMode = false
                                    onSelected(it.value)
                                }
                                .fillMaxWidth()

                        ) {
                            if (it.icon != null) {
                                Image(painter = it.icon, contentDescription = it.title, modifier = Modifier.size(32.dp))
                            }
                            ListItemText(text = it.title, modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            },
            modifier = Modifier.defaultMinSize(minWidth = 300.dp)
        )
    }
}