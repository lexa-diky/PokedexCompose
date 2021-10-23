package com.skosc.pokedex.feature.pokemondetails.battle

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonAbility
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.entity.primaryType
import com.skosc.pokedex.domain.settings.utils.localized
import com.skosc.pokedex.feature.abilitydetails.AbilityDetailsDestination
import com.skosc.pokedex.feature.pokemondetails.R
import com.skosc.pokedex.navigation.LocalNavController
import com.skosc.pokedex.navigation.navigate
import com.skosc.pokedex.uikit.coloristics.ColorPicker
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.util.local
import com.skosc.pokedex.uikit.widget.Capsule
import com.skosc.pokedex.uikit.widget.CapsuleSize
import com.skosc.pokedex.uikit.widget.PokeLabel
import com.skosc.pokedex.uikit.widget.PokeSubLabel

@Composable
fun PokemonDetailsBattlePage(species: PokemonSpecies, pokemon: Pokemon) {
    val viewModel: PokemonDetailsBattlePageViewModel = diViewModel(arg = pokemon)

    val state by viewModel.state.collectAsState()
    if (state == null) {
        return
    }
    val abilities = state!!.abilities

    val isHiddenText = stringResource(id = R.string.pokemon_details_page_battle_abilities_hidden)

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PokeLabel(
            text = stringResource(id = R.string.pokemon_details_page_battle_abilities_title),
            secondary = false
        )

        abilities.chunked(2).forEach { chunk ->
            if (chunk.size > 1) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    val first = chunk.first()
                    val second = chunk[1]

                    AbilityCapsule(first, pokemon, isHiddenText)

                    PokeSubLabel(text = stringResource(id = R.string.pokemon_details_page_battle_abilities_separator))

                    AbilityCapsule(second, pokemon, isHiddenText)

                }
            } else {
                val ability = chunk.first()
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AbilityCapsule(ability, pokemon, isHiddenText)
                }
            }
        }

        PokeLabel(
            text = stringResource(id = R.string.pokemon_details_page_battle_moves_title),
            secondary = false
        )
    }
}

@Composable
private fun RowScope.AbilityCapsule(ability: PokemonAbility, pokemon: Pokemon, isHiddenText: String) {
    val navController = LocalNavController.current

    Capsule(
        label = ability.localized,
        background = ColorPicker.getPokeColorForType(pokemon.primaryType.defaultName).local,
        size = CapsuleSize.Large,
        subLabel = if (ability.isHidden) isHiddenText else null,
        onClick = {
            navController.navigate(
                AbilityDetailsDestination, mapOf(
                    AbilityDetailsDestination.ARG_ABILITY_LINK to ability.link
                )
            )
        },
        modifier = Modifier.Companion.weight(1f),
    )
}
