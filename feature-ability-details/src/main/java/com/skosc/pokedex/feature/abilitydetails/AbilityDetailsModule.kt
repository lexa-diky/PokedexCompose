package com.skosc.pokedex.feature.abilitydetails

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.domain.pokemon.entity.PokemonAbilityLink
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val AbilityDetailsModule = PokeModule("ability_details") {
    bind<AbilityDetailsViewModel>() with factory { arg: PokemonAbilityLink -> AbilityDetailsViewModel(instance(), arg) }
}