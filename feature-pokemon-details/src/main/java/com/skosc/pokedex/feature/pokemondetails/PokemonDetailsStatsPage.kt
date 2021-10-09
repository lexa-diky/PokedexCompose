package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonStat
import com.skosc.pokedex.domain.pokemon.entity.primaryType
import com.skosc.pokedex.uikit.coloristics.ColorPicker
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.widget.FillBar
import com.skosc.pokedex.uikit.widget.PokeLabel

@Composable
fun PokemonDetailsStatsPage(pokemon: Pokemon) {
    val coloristic = LocalColoristic.current

    Column {
        pokemon.baseStats.forEach { stat ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                PokeLabel(
                    text = stat.type.toString(),
                    secondary = false,
                    modifier = Modifier.weight(0.3f)
                )
                FillBar(
                    fill = stat.value.toFloat() / PokemonStat.MAX_VALUE,
                    backgroundColor = LocalColoristic.current.backgroundShadow,
                    fillColor = coloristic.from(ColorPicker.getPokeColorForType(pokemon.primaryType.defaultName)),
                    modifier = Modifier.weight(0.7f)
                        .height(8.dp)
                )
            }
        }
    }
}