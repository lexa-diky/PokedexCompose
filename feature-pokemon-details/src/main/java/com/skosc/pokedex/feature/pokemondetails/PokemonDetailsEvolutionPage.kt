package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.uikit.image.CropTransparentTransformation
import com.skosc.pokedex.uikit.widget.EvolutionChain
import com.skosc.pokedex.uikit.widget.EvolutionChainItem

@Composable
fun PokemonDetailsEvolutionPage(pokemon: PokemonSpecies) {

    Column {
        EvolutionChain(
            items = listOf(
                EvolutionChainItem(pokemon.defaultVariety.name, rememberImagePainter(data = pokemon.defaultVariety.imageUrl) {
                    transformations(
                        CropTransparentTransformation()
                    )
                }),
                EvolutionChainItem(pokemon.defaultVariety.name, rememberImagePainter(data = pokemon.defaultVariety.imageUrl) {
                    transformations(
                        CropTransparentTransformation()
                    )
                }),
                EvolutionChainItem(pokemon.defaultVariety.name, rememberImagePainter(data = pokemon.defaultVariety.imageUrl) {
                    transformations(
                        CropTransparentTransformation()
                    )
                }),
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}