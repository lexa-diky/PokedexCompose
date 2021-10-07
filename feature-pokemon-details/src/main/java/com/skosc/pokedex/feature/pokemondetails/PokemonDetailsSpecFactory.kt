package com.skosc.pokedex.feature.pokemondetails

import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings
import timber.log.Timber

class PokemonDetailsSpecFactory(
    private val repository: PokemonRepository,
) {

    suspend fun buildForId(id: Int, settings: PokeAppSettings): List<PokemonDetailsSpec> {
        val pokemonSpecies = repository.getPokemonSpecies(id)
        return pokemonSpecies.varieties.map {  pokemon ->
            PokemonDetailsSpec(
                settings = settings,
                source = { pokemonSpecies to pokemon }
            )
        }
    }
}