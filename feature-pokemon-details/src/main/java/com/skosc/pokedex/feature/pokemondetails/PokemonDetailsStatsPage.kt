package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonStat
import com.skosc.pokedex.domain.pokemon.entity.primaryType
import com.skosc.pokedex.uikit.coloristics.Coloristic
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.widget.FillBar

@Composable
fun PokemonDetailsStatsPage(pokemon: Pokemon) {

    Column {
        pokemon.baseStats.forEach { stat ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Text(
                    text = stat.type.toString(),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(0.3f)
                )
                FillBar(
                    fill = stat.value.toFloat() / PokemonStat.MAX_VALUE,
                    backgroundColor = PokeColor.ShadowGray,
                    fillColor = Coloristic.getPokeColorForType(pokemon.primaryType.defaultName),
                    modifier = Modifier.weight(0.7f)
                        .height(8.dp)
                )
            }
        }
    }
}