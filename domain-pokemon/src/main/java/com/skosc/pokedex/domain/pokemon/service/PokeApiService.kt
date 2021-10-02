package com.skosc.pokedex.domain.pokemon.service

import com.skosc.pokedex.core.network.heavyCache
import com.skosc.pokedex.domain.pokemon.entity.network.*
import com.skosc.pokedex.domain.pokemon.util.get
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal class PokeApiService(private val client: HttpClient) {

    suspend fun getPokemonSpecies(id: Int): PokeApiPokemonSpeciesSpec = coroutineScope {
        val species = client.getPokeApi<PokeApiPokemonSpecies>("pokemon-species", id)
        val pokemon = species.varieties.map { variety -> async { client.get<PokeApiPokemon>(variety.pokemon) } }
            .awaitAll()
        val types: Map<PokeApiPokemon, List<PokeApiType>> = pokemon.map { poke ->
            async {
                poke to poke.types.map {
                    async { client.get<PokeApiType>(it.type) }
                }.awaitAll()
            }
        }.awaitAll().toMap()

        PokeApiPokemonSpeciesSpec(species, pokemon, types)
    }

    suspend fun getMove(id: Int): PokeApiPokemonMove {
        return client.getPokeApi("move", id)
    }

    suspend fun getItem(id: Int): PokeApiItem {
        return client.getPokeApi("item", id)
    }

    suspend fun getPaginated(resource: PokeApiResource, offset: Int, limit: Int): PokeApiPage {
        return client.get("https://pokeapi.co/api/v2/${resource.id}?offset=$offset&limit=$limit") {
            heavyCache()
        }
    }

    private suspend inline fun <reified T> getPokeApi(url: String): T {
        return client.get(url) {
            heavyCache()
        }
    }

    private suspend inline fun <reified T> HttpClient.getPokeApi(resource: String, id: Int): T {
        return getPokeApi("https://pokeapi.co/api/v2/$resource/$id")
    }
}