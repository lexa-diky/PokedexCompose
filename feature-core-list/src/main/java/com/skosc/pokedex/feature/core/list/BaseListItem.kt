package com.skosc.pokedex.feature.core.list

import androidx.compose.ui.graphics.Color
import com.skosc.pokedex.uikit.theme.ColorDef

interface BaseListItem {

    val id: Int

    val name: String

    val tags: List<String>

    val image: String

    val leftColor: ColorDef
    val rightColor: ColorDef

    val isPlaceholder: Boolean

    companion object {

        operator fun invoke(id: Int, name: String, tags: List<String>, image: String, color: ColorDef, isPlaceholder: Boolean = false): BaseListItem {
            return object : BaseListItem {
                override val id: Int = id
                override val name: String = name
                override val tags: List<String> = tags
                override val image: String = image
                override val leftColor: ColorDef = color
                override val rightColor: ColorDef = color
                override val isPlaceholder: Boolean = isPlaceholder
            }
        }

        operator fun invoke(id: Int, name: String, tags: List<String>, image: String, leftColor: ColorDef, rightColor: ColorDef, isPlaceholder: Boolean =false): BaseListItem {
            return object : BaseListItem {
                override val id: Int = id
                override val name: String = name
                override val tags: List<String> = tags
                override val image: String = image
                override val leftColor: ColorDef = leftColor
                override val rightColor: ColorDef = rightColor
                override val isPlaceholder: Boolean = isPlaceholder
            }
        }
    }
}