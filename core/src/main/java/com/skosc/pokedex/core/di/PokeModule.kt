package com.skosc.pokedex.core.di

import org.kodein.di.DI

fun PokeModule(name: String, init: DI.Builder.() -> Unit): DI.Module = DI.Module(
    name,
    false,
     "poke_",
    init
)
