package com.skosc.pokedex.domain.ignition

import com.skosc.pokedex.core.di.PokeModule
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val IgnitionModule = PokeModule("ignition") {
    bind<IgnitionController>() with singleton { IgnitionController(instance()) }
}
