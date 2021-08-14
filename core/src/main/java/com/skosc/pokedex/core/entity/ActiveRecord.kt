package com.skosc.pokedex.core.entity

interface ActiveRecord<T> {

    suspend fun get(): T

    companion object {

        operator fun <T> invoke(fn: () -> T): ActiveRecord<T> = object : ActiveRecord<T> {
            override suspend fun get(): T = fn()
        }
    }
}