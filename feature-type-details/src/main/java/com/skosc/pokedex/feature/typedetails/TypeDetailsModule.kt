package com.skosc.pokedex.feature.typedetails

import com.skosc.pokedex.core.di.PokeModule
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected
import org.kodein.di.bind
import org.kodein.di.factory
import org.kodein.di.instance

val TypeDetailsModule = PokeModule("type_details") {
    bind<TypeDetailsBottomSheetViewModel>() with factory { arg: TypeDetailsInit -> TypeDetailsBottomSheetViewModel(instance(), arg) }
}