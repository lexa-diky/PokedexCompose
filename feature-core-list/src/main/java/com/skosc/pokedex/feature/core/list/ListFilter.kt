package com.skosc.pokedex.feature.core.list

sealed interface ListFilter<T> {

    class Field<T>(val title: String, action: suspend (Boolean) -> Boolean): ListFilter<T>
}