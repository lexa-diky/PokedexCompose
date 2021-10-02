package com.skosc.pokedex.domain.ignition

import com.skosc.pokedex.domain.pokemon.repository.PokemonRepository
import kotlinx.coroutines.*

class IgnitionController(
    private val pokemonRepository: PokemonRepository
) {
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> Unit }
    var isIgnited: Boolean = false

    @Synchronized
    suspend fun ignite() {
        if (isIgnited) return
        isIgnited = true

        withContext(Dispatchers.IO + coroutineExceptionHandler) {
            (1..100).forEach { idx -> launch(coroutineExceptionHandler) { pokemonRepository.getPokemonSpecies(idx) } }
        }
    }
}