package com.skosc.pokedex.feature.core.details.entity

interface DetailsHeaderItem {

    val title: String

    val order: Int

    val tags: List<String>

    val image: String

    companion object {

        operator fun invoke(title: String, order: Int, tags: List<String>, image: String): DetailsHeaderItem = object : DetailsHeaderItem {
            override val title: String = title
            override val order: Int = order
            override val tags: List<String> = tags
            override val image: String = image
        }
    }
}