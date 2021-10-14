package com.skosc.pokedex.feature.typedetails

import android.os.Parcelable
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected
import java.io.Serializable

data class TypeDetailsInit(
    val primaryType: PokemonTypeExpected,
    val secondaryType: PokemonTypeExpected?,
    val isEditable: Boolean
): Serializable