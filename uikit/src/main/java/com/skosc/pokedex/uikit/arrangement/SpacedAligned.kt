package com.skosc.pokedex.uikit.arrangement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.min

data class SpacedAligned(
    val space: Dp,
    val rtlMirror: Boolean,
    val alignment: ((Int, LayoutDirection) -> Int)?
) : Arrangement.HorizontalOrVertical {

    override val spacing = space

    override fun Density.arrange(
        totalSize: Int,
        sizes: IntArray,
        layoutDirection: LayoutDirection,
        outPositions: IntArray
    ) {
        if (sizes.isEmpty()) return
        val spacePx = space.roundToPx()

        var occupied = 0
        var lastSpace = 0
        sizes.forEachIndexed { index, it ->
            outPositions[index] = min(occupied, totalSize - it)
            lastSpace = min(spacePx, totalSize - outPositions[index] - it)
            occupied = outPositions[index] + it + lastSpace
        }
        occupied -= lastSpace

        if (alignment != null && occupied < totalSize) {
            val groupPosition = alignment.invoke(totalSize - occupied, layoutDirection)
            for (index in outPositions.indices) {
                outPositions[index] += groupPosition
            }
        }
    }

    override fun Density.arrange(
        totalSize: Int,
        sizes: IntArray,
        outPositions: IntArray
    ) = arrange(totalSize, sizes, LayoutDirection.Ltr, outPositions)

    override fun toString() =
        "${if (rtlMirror) "" else "Absolute"}Arrangement#spacedAligned($space, $alignment)"
}

@Stable
fun Arrangement.arrangeBy(space: Dp, alignment: Alignment.Horizontal): Arrangement.Horizontal =
    SpacedAligned(space, true) { size, layoutDirection ->
        alignment.align(0, size, layoutDirection)
    }
