package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.entity.primaryType
import com.skosc.pokedex.uikit.coloristics.ColorPicker
import com.skosc.pokedex.uikit.theme.CardShape
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.widget.FillBar
import com.skosc.pokedex.uikit.widget.PokeLabel
import com.skosc.pokedex.uikit.widget.PokeText
import java.util.*

@Composable
fun PokemonDetailsInfoPage(species: PokemonSpecies, pokemon: Pokemon) {
    val coloristic = LocalColoristic.current

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            PokeLabel(
                text = "Generation: ",
                secondary = false,
                modifier = Modifier.weight(0.3f)
            )

            PokeLabel(
                text = species.generation.uppercase(),
                secondary = false,
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
            PokeLabel(
                text = "Catch Rate:",
                secondary = false,
                modifier = Modifier.weight(0.3f)
            )
            FillBar(
                fill = species.catchRate.toFloat() / Pokemon.MAX_CATCH_RATE,
                backgroundColor = LocalColoristic.current.accentShadow,
                fillColor = coloristic.from(ColorPicker.getPokeColorForType(pokemon.primaryType.defaultName)),
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
                    .background(LocalColoristic.current.accentShadow, CardShape)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            species.normalizedFlavorText.forEach { (version, text) ->
                PokeLabel(
                    secondary = false,
                    text = version
                )

                Spacer(modifier = Modifier.height(8.dp))

                PokeText(
                    text = text,
                    secondary = false
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