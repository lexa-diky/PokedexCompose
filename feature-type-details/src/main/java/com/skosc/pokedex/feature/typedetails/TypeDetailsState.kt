package com.skosc.pokedex.feature.typedetails

import com.skosc.pokedex.domain.pokemon.entity.PokemonType
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected
import com.skosc.pokedex.domain.pokemon.entity.PokemonWeaknessRelation

data class TypeDetailsState(
    val primaryType: PokemonType,
    val secondaryType: PokemonType?,
    val allTypes: List<PokemonType>,
    val isEditable: Boolean,
    val showTypeSelection: Boolean
) {

    val relations: List<PokemonWeaknessRelation> = sumRelations(primaryType.relations, secondaryType?.relations)

    private fun sumRelations(primary: List<PokemonWeaknessRelation>, secondary: List<PokemonWeaknessRelation>?): List<PokemonWeaknessRelation> {
        if (secondary == null) {
            return primary
        }
        val sum = primary + secondary
        val grouped: Map<Pair<PokemonTypeExpected, Boolean>, List<PokemonWeaknessRelation>> = sum.groupBy { it.other to it.magnitude.isTo }
        return grouped.values.mapNotNull { relations ->
            when (relations.size) {
                1 -> relations.first()
                2 -> {
                    val first = relations.first()
                    val second = relations[1]
                    val resultMagnitude = first.magnitude + second.magnitude
                    if (resultMagnitude == null) {
                        null
                    } else {
                        PokemonWeaknessRelation(first.type, first.other, resultMagnitude)
                    }
                }
                else -> relations.first()
            }
        }
    }
}