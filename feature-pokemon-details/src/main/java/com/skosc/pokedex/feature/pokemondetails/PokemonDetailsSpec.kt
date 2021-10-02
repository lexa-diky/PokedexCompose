package com.skosc.pokedex.feature.pokemondetails

import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import com.skosc.pokedex.domain.pokemon.util.getLocalized
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings
import com.skosc.pokedex.feature.core.details.GenericDetailsSpec
import com.skosc.pokedex.feature.core.details.entity.DetailsBackground
import com.skosc.pokedex.feature.core.details.entity.DetailsHeaderItem
import com.skosc.pokedex.feature.core.details.entity.DetailsPageItem
import com.skosc.pokedex.feature.core.details.entity.TabRowItem
import com.skosc.pokedex.uikit.coloristics.Coloristic

class PokemonDetailsSpec(
    private val repository: PokemonRepository,
    private val settings: PokeAppSettings,
    private val id: Int
) : GenericDetailsSpec<PokemonSpecies> {

    override val source: suspend () -> PokemonSpecies = {
        repository.getPokemonSpecies(id)
    }

    override val headerMapper: (PokemonSpecies) -> DetailsHeaderItem = { pokemon ->
        DetailsHeaderItem(
            title = pokemon.defaultVariety.name,
            order = id,
            tags = pokemon.defaultVariety.types.map { it.names.getLocalized(settings.localization) },
            image = pokemon.defaultVariety.imageUrl
        )
    }

    override val pagesMapper: (PokemonSpecies) -> List<DetailsPageItem> = { pokemon ->
        listOf(
            DetailsPageItem(TabRowItem("Stats")) { PokemonDetailsStatsPage(pokemon) },
            DetailsPageItem(TabRowItem("Info")) { PokemonDetailsInfoPage(pokemon) },
            DetailsPageItem(TabRowItem("Evolution")) { PokemonDetailsEvolutionPage(pokemon) }
        )
    }

    override val backgroundMapper: (PokemonSpecies) -> DetailsBackground = { pokemon ->
        val left = Coloristic.getPokeColorForType(pokemon.defaultVariety.types.first().defaultName)
        DetailsBackground(
            left = left,
            right = pokemon.defaultVariety.types.getOrNull(1)?.defaultName?.let(Coloristic::getPokeColorForType)
                ?: left,
            base = Coloristic.getPokeColorForName(pokemon.color.name)
        )
    }
}