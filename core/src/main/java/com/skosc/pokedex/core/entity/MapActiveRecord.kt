package com.skosc.pokedex.core.entity

class MapActiveRecord<F, T>(private val source: ActiveRecord<F>, private val mapper: (F) -> T) : ActiveRecord<T> {

    override suspend fun get(): T = mapper(source.get())
}

fun <F, T> List<ActiveRecord<F>>.activeMap(fn: (F) -> T): List<ActiveRecord<T>> {
    return map { MapActiveRecord(it, fn) }
}
