package com.skosc.pokedex.domain.pokemon.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>,
    val baseStats: List<PokemonStat>,
    val abilities: List<PokemonAbilityLink>
) {

    companion object {

        const val MAX_CATCH_RATE = 100
    }
}

val Pokemon.primaryType: PokemonType get() = types.first()
val Pokemon.secondaryType: PokemonType? get() = types.getOrNull(1)