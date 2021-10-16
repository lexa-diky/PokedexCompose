package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.domain.pokemon.entity.Pokemon
import com.skosc.pokedex.domain.pokemon.entity.PokemonAbilityLink
import com.skosc.pokedex.domain.pokemon.entity.PokemonStat
import com.skosc.pokedex.domain.pokemon.entity.PokemonStatType
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiPokemonSpec
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiStat
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiStatType

internal object PokeApiPokemonMapper {

    fun map(spec: PokeApiPokemonSpec): Pokemon {
        return Pokemon(
            id = spec.pokemon.id,
            name = spec.pokemon.name,
            imageUrl = spec.pokemon.sprites.frontDefault,
            types = spec.types.map { PokeApiTypeMapper.map(it) },
            baseStats = mapStats(spec.pokemon.stats),
            abilities = mapAbilityLinks(spec)
        )
    }

    private fun mapAbilityLinks(spec: PokeApiPokemonSpec) = spec.pokemon.abilities.map {
        PokemonAbilityLink(
            resource = it.ability.name!!,
            isHidden = it.isHidden
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