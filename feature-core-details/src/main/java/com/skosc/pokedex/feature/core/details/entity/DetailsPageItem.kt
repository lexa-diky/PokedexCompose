package com.skosc.pokedex.feature.core.details.entity

import androidx.compose.runtime.Composable

interface DetailsPageItem {

    val title: TabRowItem

    val content: @Composable () -> Unit

    companion object {

        operator fun invoke(title: TabRowItem, content: @Composable () -> Unit): DetailsPageItem = object : DetailsPageItem {
            override val title: TabRowItem = title
            override val content: @Composable () -> Unit = content
        }
    }
}