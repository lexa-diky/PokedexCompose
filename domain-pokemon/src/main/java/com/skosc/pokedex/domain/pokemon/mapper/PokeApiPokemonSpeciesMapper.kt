package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.core.entity.activeMap
import com.skosc.pokedex.domain.pokemon.entity.*
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonSpec
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonSpecies
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonSpeciesSpec

internal object PokeApiPokemonSpeciesMapper {

    fun map(spec: PokeApiPokemonSpeciesSpec): PokemonSpecies {
        return PokemonSpecies(
            id = spec.species.id,
            color = PokemonColor(spec.species.color?.name ?: "red"),
            catchRate = spec.species.captureRate,
            flavorText = spec.species.flavorText
                .filter { it.language.name == "en" }
                .map {
                    PokemonFlavorText(
                        it.text,
                        Language.ENGLISH,
                        PokemonVersion(it.version.name)
                    )
                },
            generation = spec.species.generation.name,
            varieties = spec.pokemon.map { PokeApiPokemonMapper.map(PokeApiPokemonSpec(it, spec.species)) }
        )
    }
}