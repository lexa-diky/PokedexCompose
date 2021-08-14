package com.skosc.pokedex.feature.core.details.entity

interface BaseDetailsItem {

    val header: DetailsHeaderItem

    val pages: List<DetailsPageItem>

    companion object {

        operator fun invoke(header: DetailsHeaderItem, pages: List<DetailsPageItem>): BaseDetailsItem = object : BaseDetailsItem {
            override val header: DetailsHeaderItem = header
            override val pages: List<DetailsPageItem> = pages
        }
    }
}