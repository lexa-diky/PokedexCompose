package com.skosc.pokedex.feature.pokemondetails

import androidx.compose.runtime.Composable
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.util.getLocalized
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings
import com.skosc.pokedex.feature.core.details.GenericDetailsSpec
import com.skosc.pokedex.feature.core.details.entity.DetailsBackground
import com.skosc.pokedex.feature.core.details.entity.DetailsHeaderItem
import com.skosc.pokedex.feature.core.details.entity.DetailsPageItem
import com.skosc.pokedex.feature.core.details.entity.TabRowItem
import com.skosc.pokedex.uikit.coloristics.ColorPicker

class PokemonDetailsSpec(
    private val sid: Int,
    private val settings: PokeAppSettings,
    override val source: suspend () -> Pair<PokemonSpecies, Pokemon>
) : GenericDetailsSpec<Pair<PokemonSpecies, Pokemon>> {

    override val headerMapper: (Pair<PokemonSpecies, Pokemon>) -> DetailsHeaderItem = { (species, pokemon) ->
        DetailsHeaderItem(
            title = pokemon.name,
            order = species.id, // TODO change to order
            tags = pokemon.types.map { it.names.getLocalized(settings.localization) },
            image = pokemon.imageUrl
        )
    }

    override val pagesMapper: (Pair<PokemonSpecies, Pokemon>) -> List<DetailsPageItem> = { (species, pokemon) ->
        listOf(
            DetailsPageItem(TabRowItem("Stats")) { PokemonDetailsStatsPage(pokemon) },
            DetailsPageItem(TabRowItem("Info")) { PokemonDetailsInfoPage(species, pokemon) },
            DetailsPageItem(TabRowItem("Evolution")) { PokemonDetailsEvolutionPage(pokemon) }
        )
    }

    override val backgroundMapper: (Pair<PokemonSpecies, Pokemon>) -> DetailsBackground = { (species, pokemon) ->
        val left = ColorPicker.getPokeColorForType(pokemon.types.first().defaultName)
        val right = (pokemon.types.getOrNull(1)?.defaultName?.let(ColorPicker::getPokeColorForType) ?: left)
        DetailsBackground(
            left = if (sid.mod(2) == 0) left else right,
            right = if (sid.mod(2) == 0) right else left,
            base = left
        )
    }
}