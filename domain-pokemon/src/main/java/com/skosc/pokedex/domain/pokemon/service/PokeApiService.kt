package com.skosc.pokedex.domain.pokemon.service

import com.skosc.pokedex.core.network.heavyCache
import com.skosc.pokedex.core.util.Memoize
import com.skosc.pokedex.domain.pokemon.entity.network.*
import com.skosc.pokedex.domain.pokemon.util.get
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.ConcurrentHashMap

internal class PokeApiService(private val client: HttpClient) {

    private val cache = ConcurrentHashMap<Any, Any>()

    suspend fun getPokemonSpecies(id: Int): PokeApiPokemonSpeciesSpec {
        return Memoize(cache, id) {
            val species = client.getPokeApi<PokeApiPokemonSpecies>(PokeApiResource.PokemonSpecies.id, id)
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
    }

    suspend fun getMove(id: Int): PokeApiPokemonMove {
        return client.getPokeApi(PokeApiResource.Move.id, id)
    }

    suspend fun getItem(id: Int): PokeApiItem {
        return client.getPokeApi(PokeApiResource.Item.id, id)
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