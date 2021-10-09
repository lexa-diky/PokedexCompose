package com.skosc.pokedex.uikit.widget.filter

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.widget.PokeText

@Composable
fun TagContainerScope.FieldFilterTag(modifier: Modifier = Modifier, default: String = "", placeholder: String = "") {
    var text by remember { mutableStateOf(TextFieldValue(default)) }
    BasicTextField(
        value = text,
        onValueChange = { text = it },
        decorationBox = { innerTextField ->
            if (text.text.isEmpty() && placeholder.isNotBlank()) {
                PokeText(placeholder, onCard = false)
            }
            innerTextField()
        },
        modifier = modifier
    )
}