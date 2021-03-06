package com.skosc.pokedex.page.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.theme.ListItemShape
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.widget.PokeLink

@Composable
fun Basement(text: String, onSettingsClicked: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(LocalColoristic.current.backgroundShadow)
            .fillMaxWidth()
            .height(64.dp)
    ) {
        PokeLink(
            text = text,
            modifier = Modifier
                .clip(ListItemShape)
                .clickable { onSettingsClicked() }
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}