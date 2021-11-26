package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.domain.pokemon.entity.PokemonType
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.typedetails.TypeDetailsInit
import com.skosc.pokedex.feature.typedetails.TypeDetailsPage
import com.skosc.pokedex.navigation.LocalNavigator
import com.skosc.pokedex.uikit.widget.OrderText
import com.skosc.pokedex.uikit.widget.SubPokeHeader
import com.skosc.pokedex.uikit.widget.TypeChip

@Composable
fun PokemonDetailsHeader(
    order: Int,
    title: String,
    types: List<PokemonType>,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.current
    val settings = LocalSettings.current

    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 48.dp)
            .then(modifier)
    ) {
        Column {
            SubPokeHeader(text = title, secondary = true)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                types.forEach { type ->
                    TypeChip(
                        type = type.getLocalized(settings.localization),
                        onClick = {
                            navigator.navigate(
                                TypeDetailsPage, TypeDetailsInit(
                                    primaryType = types.first().expected,
                                    secondaryType = types.getOrNull(1)?.expected,
                                    isEditable = false
                                )
                            )
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OrderText(order)
    }
}
