package com.skosc.pokedex.core.entity

class MapActiveRecord<F, T>(private val source: ActiveRecord<F>, private val mapper: suspend (F) -> T) :
    ActiveRecord<T> {

    override suspend fun get(): T = mapper(source.get())
}

fun <F, T> ActiveRecord<F>.map(fn: suspend (F) -> T): ActiveRecord<T> {
    return MapActiveRecord(this, fn)
}


fun <F, T> List<ActiveRecord<F>>.activeMap(fn: suspend (F) -> T): List<ActiveRecord<T>> {
    return map { MapActiveRecord(it, fn) }
}
