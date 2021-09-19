package com.skosc.pokedex.domain.pokemon

import androidx.paging.PagingSource
import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonItem
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.pokemon.entity.PokemonSpecies
import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import com.skosc.pokedex.domain.pokemon.service.PokeApiService
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val PokemonDomainModule = PokeModule("domain_pokemon") {
    bind<PokeApiService>() with singleton { PokeApiService(instance()) }
    bind<PokemonRepository>() with singleton { PokemonRepository(instance()) }

    bind<PagingSource<Int, PokemonSpecies>>() with singleton { createPokemonPagingSource(instance()) }
    bind<PagingSource<Int, PokemonMove>>() with singleton { createMoveDataSource(instance()) }
    bind<PagingSource<Int, PokemonItem>>() with singleton { createItemDataSource(instance()) }
}

private fun createPokemonPagingSource(repo: PokemonRepository): PagingSource<Int, PokemonSpecies> {
    return repo.getPokemonSpeciesPagingSource()
}

private fun createMoveDataSource(repo: PokemonRepository): PagingSource<Int, PokemonMove> {
    return repo.getMovePagingSource()
}

private fun createItemDataSource(repo: PokemonRepository): PagingSource<Int, PokemonItem> {
    return repo.getItemPagingSource()
}
