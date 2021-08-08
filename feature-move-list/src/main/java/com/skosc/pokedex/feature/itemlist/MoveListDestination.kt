package com.skosc.pokedex.feature.itemlist

import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.navigation.ParcelableVoid

object MoveListDestination : Destination<ParcelableVoid> {
    override val path: String = "/move-list"
    override val arguments: ParcelableVoid = ParcelableVoid
}
