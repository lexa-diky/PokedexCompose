package com.skosc.pokedex.feature.pokemondetails

import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import com.skosc.pokedex.feature.core.details.GenericDetailsSpec
import com.skosc.pokedex.feature.core.details.entity.DetailsHeaderItem
import com.skosc.pokedex.feature.core.details.entity.DetailsPageItem
import com.skosc.pokedex.feature.core.details.entity.TabRowItem

class PokemonDetailsSpec(
    private val repository: PokemonRepository,
    private val order: Int
) : GenericDetailsSpec<Pokemon> {

    override val source: suspend () -> Pokemon = {
        repository.getPokemon(order)
    }

    override val headerMapper: (Pokemon) -> DetailsHeaderItem = { pokemon ->
        DetailsHeaderItem(
            title = pokemon.name,
            order = order,
            tags = pokemon.types,
            image = pokemon.imageUrl
        )
    }

    override val pagesMapper: (Pokemon) -> List<DetailsPageItem> = { pokemon ->
        listOf(
            DetailsPageItem(TabRowItem("Stats")) { PokemonDetailsStatsPage(pokemon) },
            DetailsPageItem(TabRowItem("Info")) { PokemonDetailsInfoPage(pokemon) },
            DetailsPageItem(TabRowItem("Evolution")) { PokemonDetailsEvolutionPage(pokemon) }
        )
    }
}