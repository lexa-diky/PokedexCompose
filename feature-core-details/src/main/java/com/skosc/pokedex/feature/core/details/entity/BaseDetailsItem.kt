package com.skosc.pokedex.feature.core.details.entity

interface BaseDetailsItem {

    val header: DetailsHeaderItem

    val pages: List<DetailsPageItem>

    val background: DetailsBackground

    companion object {

        operator fun invoke(
            header: DetailsHeaderItem,
            pages: List<DetailsPageItem>,
            background: DetailsBackground
        ): BaseDetailsItem = object : BaseDetailsItem {
            override val header: DetailsHeaderItem = header
            override val pages: List<DetailsPageItem> = pages
            override val background: DetailsBackground = background
        }
    }
}