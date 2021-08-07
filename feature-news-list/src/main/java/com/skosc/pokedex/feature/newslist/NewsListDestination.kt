package com.skosc.pokedex.feature.newslist

import com.skosc.pokedex.navigation.Destination
import com.skosc.pokedex.navigation.ParcelableVoid

object NewsListDestination : Destination<ParcelableVoid> {
    override val path: String = "news-list/"
    override val arguments: ParcelableVoid = ParcelableVoid
}