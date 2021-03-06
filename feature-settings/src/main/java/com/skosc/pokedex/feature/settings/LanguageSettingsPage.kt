package com.skosc.pokedex.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.core.localization.Localization
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.theme.ListItemShape
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.widget.PokeCardHeader
import com.skosc.pokedex.uikit.widget.PokeCardSubHeader

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LanguageSettingsPage(onFinished: () -> Unit) {
    val coloristic = LocalColoristic.current
    val viewModel: LanguageSettingsViewModel = diViewModel()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(vertical = 32.dp),
        modifier = Modifier.background(coloristic.backgroundAccent)
    ) {
        items(Localization.values()) { item ->
            LanguageSettingsItem(item.name, item.name) {
                viewModel.updateLanguage(item)
                onFinished()
            }
        }
    }
}

@Composable
private fun LanguageSettingsItem(localName: String, globalName: String, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(ListItemShape)
            .clickable { onClick() }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        PokeCardHeader(text = localName, secondary = false)
        PokeCardSubHeader(text = globalName, secondary = false)
    }
}