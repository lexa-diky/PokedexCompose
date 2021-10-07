package com.skosc.pokedex.feature.core.list

import androidx.compose.ui.graphics.Color

interface BaseListItem {

    val id: Int

    val name: String

    val tags: List<String>

    val image: String

    val leftColor: Color
    val rightColor: Color

    companion object {

        operator fun invoke(id: Int, name: String, tags: List<String>, image: String, color: Color): BaseListItem {
            return object : BaseListItem {
                override val id: Int = id
                override val name: String = name
                override val tags: List<String> = tags
                override val image: String = image
                override val leftColor: Color = color
                override val rightColor: Color = color
            }
        }

        operator fun invoke(id: Int, name: String, tags: List<String>, image: String, leftColor: Color, rightColor: Color): BaseListItem {
            return object : BaseListItem {
                override val id: Int = id
                override val name: String = name
                override val tags: List<String> = tags
                override val image: String = image
                override val leftColor: Color = leftColor
                override val rightColor: Color = rightColor
            }
        }
    }
}