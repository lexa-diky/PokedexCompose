package com.skosc.pokedex.feature.itemlist

import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.navigation.ParcelableVoid

object ItemListDestination : Destination<ParcelableVoid> {
    override val path: String = "/item-list"
    override val arguments: ParcelableVoid = ParcelableVoid
}
