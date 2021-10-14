package com.skosc.pokedex.feature.typedetails

import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected

sealed interface TypeDetailsEvent {

    object ShowTypeSelectDialog : TypeDetailsEvent

    object DismissSelection : TypeDetailsEvent

    class TypesSelected(val types: Pair<PokemonTypeExpected, PokemonTypeExpected?>): TypeDetailsEvent
}