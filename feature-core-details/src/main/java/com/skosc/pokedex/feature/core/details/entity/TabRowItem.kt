package com.skosc.pokedex.feature.core.details.entity

interface TabRowItem {

    val title: String

    companion object {

        operator fun invoke(title: String): TabRowItem = object : TabRowItem {
            override val title: String = title
        }
    }
}