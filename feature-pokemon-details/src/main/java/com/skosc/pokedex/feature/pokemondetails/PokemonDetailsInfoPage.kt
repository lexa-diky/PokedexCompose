package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.entity.PokemonStat
import com.skosc.pokedex.uikit.theme.CardShape
import com.skosc.pokedex.uikit.theme.UIColor
import com.skosc.pokedex.uikit.widget.FillBar
import com.skosc.pokedex.uikit.widget.StatBar
import java.util.*

@Composable
fun PokemonDetailsInfoPage(pokemon: PokemonSpecies) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Text(
                text = "Generation: ",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(0.3f)
            )

            Text(
                text = pokemon.generation.uppercase(),
                modifier = Modifier
                    .weight(0.7f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Text(
                text = "Catch Rate:",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(0.3f)
            )
            FillBar(
                fill = pokemon.catchRate.toFloat() / Pokemon.MAX_CATCH_RATE,
                backgroundColor = Color.Gray,
                fillColor = Color.Green,
                modifier = Modifier
                    .height(8.dp)
                    .weight(0.7f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(UIColor.ShadowGray, CardShape)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            pokemon.normalizedFlavorText.forEach { (version, text) ->
                Text(
                    fontWeight = FontWeight.SemiBold,
                    text = version
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = text
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

private val PokemonSpecies.normalizedFlavorText
    get() = flavorText
        .shuffled()
        .take(3)
        .map {
            it.version.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } to it.text.replace(
                "\n",
                ""
            )
        }