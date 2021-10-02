package com.skosc.pokedex.feature.core.details

import com.skosc.pokedex.feature.core.details.entity.DetailsBackground
import com.skosc.pokedex.feature.core.details.entity.DetailsHeaderItem
import com.skosc.pokedex.feature.core.details.entity.DetailsPageItem

interface GenericDetailsSpec<T> {

    val source: suspend () -> T

    val headerMapper: (T) -> DetailsHeaderItem

    val pagesMapper: (T) -> List<DetailsPageItem>

    val backgroundMapper: (T) -> DetailsBackground
}