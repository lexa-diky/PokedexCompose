package com.skosc.pokedex.domain.pokemon.entity

import java.io.Serializable

class PokemonAbilityLink(
    override val resource: String,
    val isHidden: Boolean
) : Link<PokemonAbility>, Serializable
