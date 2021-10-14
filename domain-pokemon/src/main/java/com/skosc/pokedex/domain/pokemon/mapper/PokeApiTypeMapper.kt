package com.skosc.pokedex.domain.pokemon.mapper

import com.skosc.pokedex.domain.pokemon.entity.EntityName
import com.skosc.pokedex.domain.pokemon.entity.PokemonType
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected
import com.skosc.pokedex.domain.pokemon.entity.PokemonWeaknessRelation
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiDamageRelations
import com.skosc.pokedex.domain.pokemon.entity.network.PokeApiType

internal object PokeApiTypeMapper {

    fun map(type: PokeApiType): PokemonType = PokemonType(
        id = type.id,
        defaultName = type.name,
        names = type.names.map { EntityName(it.name, it.language.name) },
        relations = map(type.name, type.damageRelations)
    )

    private fun map(base: String, relations: PokeApiDamageRelations): List<PokemonWeaknessRelation> {
        val baseType = PokemonTypeExpected.from(base)
        return mapBindings(baseType, relations.doubleDamageFrom, PokemonWeaknessRelation.Magnitude.DOUBLE_FROM) +
                mapBindings(baseType, relations.doubleDamageTo, PokemonWeaknessRelation.Magnitude.DOUBLE_TO) +
                mapBindings(baseType, relations.halfDamageFrom, PokemonWeaknessRelation.Magnitude.HALF_FROM) +
                mapBindings(baseType, relations.halfDamageTo, PokemonWeaknessRelation.Magnitude.HALF_TO) +
                mapBindings(baseType, relations.noDamageFrom, PokemonWeaknessRelation.Magnitude.NO_FROM) +
                mapBindings(baseType, relations.noDamageTo, PokemonWeaknessRelation.Magnitude.NO_TO)
    }

    private fun mapBindings(
        base: PokemonTypeExpected,
        bindings: List<PokeApiDamageRelations.Binding>,
        magnitude: PokemonWeaknessRelation.Magnitude
    ): List<PokemonWeaknessRelation> {
        return bindings.map { binding ->
            val target = PokemonTypeExpected.from(binding.name)
            PokemonWeaknessRelation(base, target, magnitude)
        }
    }
}