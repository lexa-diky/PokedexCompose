package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.core.entity.ActiveRecord
import com.skosc.pokedex.core.entity.activeMap
import com.skosc.pokedex.core.entity.map
import com.skosc.pokedex.domain.pokemon.entity.*
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonSpec
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiStat
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiStatType

internal object PokeApiPokemonMapper {

    fun map(spec: PokeApiPokemonSpec): Pokemon {
        return Pokemon(
            id = spec.pokemon.id,
            name = spec.pokemon.name,
            imageUrl = spec.pokemon.sprites.frontDefault,
            types = spec.pokemon.types.map { it.type.name },
            baseStats = mapStats(spec.pokemon.stats),
        )
    }

    private fun mapStats(stats: List<PokeApiStat>): List<PokemonStat> {
        return stats.map { apiStat ->
            PokemonStat(
                value = apiStat.baseStatValue,
                type = when (apiStat.stat.name) {
                    PokeApiStatType.Hp -> PokemonStatType.Hp
                    PokeApiStatType.Attack -> PokemonStatType.Attack
                    PokeApiStatType.SpAttack -> PokemonStatType.SpAttack
                    PokeApiStatType.Defence -> PokemonStatType.Defence
                    PokeApiStatType.SpDefence -> PokemonStatType.SpDefence
                    PokeApiStatType.Speed -> PokemonStatType.Speed
                }
            )
        }
    }
}