package com.skosc.pokedex.domain.pokemon.service

import arrow.core.Either
import com.skosc.pokedex.core.network.entity.activeNetwork
import com.skosc.pokedex.core.network.heavyCache
import com.skosc.pokedex.core.util.await
import com.skosc.pokedex.core.util.unwrapToString
import com.skosc.pokedex.domain.pokemon.entity.PokemonMove
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemon
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonMove
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonSpec
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonSpecies
import com.skosc.pokedex.domain.pokemon.mapper.PokeApiMoveMapper
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PokeApiService(private val client: HttpClient) {

    suspend fun getPokemon(id: Int): PokeApiPokemonSpec = coroutineScope {

        val pokemonDef = async {
            client.get<PokeApiPokemon>("https://pokeapi.co/api/v2/pokemon/$id") {
                heavyCache()
            }
        }

        val speciesDef =
            async {
                client.get<PokeApiPokemonSpecies>("https://pokeapi.co/api/v2/pokemon-species/$id") {
                    heavyCache()
                }
            }

        val (pokemon, species) = await(pokemonDef, speciesDef)

        val moves = pokemon.moves.map {
            activeNetwork { getMove(Either.Right(it.move.name)) }
        }

        PokeApiPokemonSpec(pokemon, species, moves)
    }

    suspend fun getMove(id: Int): PokeApiPokemonMove {
        return getMove(Either.Left(id))
    }

    private suspend fun getMove(idOrName: Either<Int, String>): PokeApiPokemonMove {
        return client.get("https://pokeapi.co/api/v2/move/${idOrName.unwrapToString()}") {
            heavyCache()
        }
    }
}