package com.skosc.pokedex.feature.core.details.entity

import androidx.compose.runtime.Composable

interface DetailsHeaderItem {

    val content: @Composable () -> Unit

    val image: String

    companion object {

        operator fun invoke(image: String, content: @Composable () -> Unit): DetailsHeaderItem = object : DetailsHeaderItem {
            override val image: String = image
            override val content: @Composable () -> Unit = content
        }
    }
}