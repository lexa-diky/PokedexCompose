package com.skosc.pokedex.feature.core.pokemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.skosc.pokedex.domain.pokemon.entity.PokemonType
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected

@Composable
fun PokemonTypeExpected.icon(): Painter {
    val resId = when(this) {
        PokemonTypeExpected.Normal -> R.drawable.ic_type_normal
        PokemonTypeExpected.Fighting -> R.drawable.ic_type_fighting
        PokemonTypeExpected.Flying -> R.drawable.ic_type_flying
        PokemonTypeExpected.Poison -> R.drawable.ic_type_poison
        PokemonTypeExpected.Ground -> R.drawable.ic_type_ground
        PokemonTypeExpected.Rock -> R.drawable.ic_type_rock
        PokemonTypeExpected.Bug -> R.drawable.ic_type_bug
        PokemonTypeExpected.Ghost -> R.drawable.ic_type_ghost
        PokemonTypeExpected.Steel -> R.drawable.ic_type_steel
        PokemonTypeExpected.Fire -> R.drawable.ic_type_fire
        PokemonTypeExpected.Water -> R.drawable.ic_type_water
        PokemonTypeExpected.Grass -> R.drawable.ic_type_grass
        PokemonTypeExpected.Electric -> R.drawable.ic_type_electric
        PokemonTypeExpected.Psychic -> R.drawable.ic_type_psychic
        PokemonTypeExpected.Ice -> R.drawable.ic_type_ice
        PokemonTypeExpected.Dragon -> R.drawable.ic_type_dragon
        PokemonTypeExpected.Dark -> R.drawable.ic_type_dark
        PokemonTypeExpected.Fairy -> R.drawable.ic_type_fairy
        PokemonTypeExpected.Shadow -> R.drawable.ic_type_dark
        else -> R.drawable.ic_type_normal
    }

    return painterResource(resId)
}