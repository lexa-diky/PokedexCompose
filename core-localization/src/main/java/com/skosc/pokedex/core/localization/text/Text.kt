package com.skosc.pokedex.core.localization.text

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

interface Text {

    @Composable
    fun render(): String

    data class Resource(@StringRes val id: Int, val arguments: Array<Any> = emptyArray()): Text {

        @Composable
        override fun render(): String {
            return LocalContext.current.getString(id, *arguments)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Resource

            if (id != other.id) return false
            if (!arguments.contentEquals(other.arguments)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + arguments.contentHashCode()
            return result
        }
    }

    data class Raw(val value: String): Text {

        @Composable
        override fun render(): String = value
    }

    companion object {

        operator fun invoke(value: String): Text = Raw(value)
    }
}