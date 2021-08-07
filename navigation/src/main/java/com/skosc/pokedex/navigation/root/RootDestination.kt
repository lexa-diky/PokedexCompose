package com.skosc.pokedex.navigation.root

import android.os.Parcelable
import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.navigation.ParcelableVoid

object RootDestination : Destination<ParcelableVoid> {
    override val path: String = "root/"
    override val arguments: ParcelableVoid = ParcelableVoid
}
