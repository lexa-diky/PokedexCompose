package com.skosc.pokedex.feature.core.list

fun interface BaseListItemMapper<T: Any> {

    suspend operator fun invoke(input: T): BaseListItem
}