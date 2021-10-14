package com.skosc.pokedex.feature.typedetails

import com.skosc.pokedex.navigation.Destination

object TypeDetailsDestination : Destination {
    override val path: String = "modal/type/details"

    const val ARG_INIT = "init"
}