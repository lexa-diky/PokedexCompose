package com.skosc.pokedex.core.entity

interface ActiveRecord<T> {

    suspend fun get(): T
}