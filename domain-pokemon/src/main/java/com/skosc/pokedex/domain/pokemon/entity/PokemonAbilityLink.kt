package com.skosc.pokedex.domain.pokemon.entity

class PokemonAbilityLink(
    override val resource: String,
    val isHidden: Boolean
) : Link<PokemonAbility>
