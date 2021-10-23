package com.skosc.pokedex.feature.abilitydetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.skosc.pokedex.core.util.applyIf
import com.skosc.pokedex.domain.pokemon.entity.*
import com.skosc.pokedex.domain.settings.utils.localized
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.theme.PokeShapes
import com.skosc.pokedex.uikit.theme.PokedexTheme
import com.skosc.pokedex.uikit.widget.SubPokeHeader

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.AbilityDetailsPageBottomSheet() = bottomSheet(AbilityDetailsDestination.path) { currentBackStack ->
    val abilityLink: PokemonAbilityLink = remember(currentBackStack) { currentBackStack.arguments?.getSerializable(AbilityDetailsDestination.ARG_ABILITY_LINK) as PokemonAbilityLink }

    InternalAbilityDetailsPageBottomSheet(abilityLink)
}

@Composable
private fun InternalAbilityDetailsPageBottomSheet(abilityLink: PokemonAbilityLink) {
    val viewModel: AbilityDetailsViewModel = diViewModel(arg = abilityLink)
    val state by viewModel.state.collectAsState()
    InternalAbilityDetailsPageBottomSheet(state)
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
private fun InternalAbilityDetailsPageBottomSheet(state: PokemonAbility?) {
    val coloristic = LocalColoristic.current

    AnimatedVisibility(visible = state != null, enter = expandVertically()) {
        if (state == null) return@AnimatedVisibility
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(8.dp)
        ) {
            SubPokeHeader(
                text = state.localized,
                modifier = Modifier.padding(8.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(coloristic.backgroundShadow, PokeShapes.HighlightSectionShape)
                ) {
                    Text(
                        text = state.currentEffect.localized().shortEffect,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Text(
                    text = state.currentEffect.localized().effect,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(Modifier.size(32.dp))
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun Preview_InternalAbilityDetailsPageBottomSheet() = PokedexTheme {
    InternalAbilityDetailsPageBottomSheet(
        PokemonAbility(
            name  = "Super Ability",
            names = emptyList(),
            isHidden = false,
            currentEffect = PokemonAbilityEntries(listOf(
                PokemonAbilityEntry(
                    effect = "Some Long Text",
                    shortEffect = "Short",
                    language = Language.ENGLISH
                )
            )),
            link = PokemonAbilityLink("", false)
        )
    )
}