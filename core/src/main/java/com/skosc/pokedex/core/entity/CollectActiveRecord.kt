package com.skosc.pokedex.core.entity

class CollectActiveRecord<F, T>(
    private val sources: List<ActiveRecord<F>>,
    private val mapper: (List<F>) -> T
) : ActiveRecord<T> {

    override suspend fun get(): T {
        return mapper(sources.map { it.get() })
    }
}

fun <F, T> List<ActiveRecord<F>>.collect(fn: (List<F>) -> T): ActiveRecord<T> {
    return CollectActiveRecord(this, fn)
}
