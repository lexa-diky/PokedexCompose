package com.skosc.pokedex.domain.pokemon

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import com.skosc.pokedex.domain.pokemon.service.PokeApiService
import com.skosc.pokedex.core.network.PaginatedFlowReader
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val PokemonDomainModule = PokeModule("domain_pokemon") {
    bind<PokeApiService>() with singleton { PokeApiService(instance()) }
    bind<PokemonRepository>() with singleton { PokemonRepository(instance()) }

    bind<PaginatedFlowReader<PokemonSpecies>>() with singleton { createPokemonPagingSource(instance()) }
    bind<PaginatedFlowReader<PokemonMove>>() with singleton { createMoveDataSource(instance()) }
    bind<PaginatedFlowReader<PokemonItem>>() with singleton { createItemDataSource(instance()) }
}

private fun createPokemonPagingSource(repo: PokemonRepository): PaginatedFlowReader<PokemonSpecies> {
    return repo.getPokemonSpeciesPaginatedReader()
}

private fun createMoveDataSource(repo: PokemonRepository): PaginatedFlowReader<PokemonMove> {
    return repo.getMovePaginatedReader()
}

private fun createItemDataSource(repo: PokemonRepository): PaginatedFlowReader<PokemonItem> {
    return repo.getItemPaginatedReader()
}
