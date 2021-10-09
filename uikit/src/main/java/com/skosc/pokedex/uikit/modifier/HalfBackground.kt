package com.skosc.pokedex.uikit.modifier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class HalfSplitBackground(
    private val leftColor: Color,
    private val rightColor: Color
) : DrawModifier {

    override fun ContentDrawScope.draw() {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawRect(leftColor)

        drawPath(
            path = Path().apply {
                moveTo(0f, 0f)
                lineTo(canvasWidth, canvasHeight)
                lineTo(0f, canvasWidth)
                lineTo(0f, 0f)
            },
            brush = SolidColor(leftColor)
        )

        drawPath(
            path = Path().apply {
                moveTo(0f, 0f)
                lineTo(canvasWidth, canvasHeight)
                lineTo(canvasWidth, 0f)
                lineTo(0f, 0f)
            },
            brush = SolidColor(rightColor)
        )

        drawContent()
    }
}

fun Modifier.halfBackground(leftColor: Color, rightColor: Color): Modifier =
    then(HalfSplitBackground(leftColor, rightColor))

fun Modifier.halfBackground(leftColor: Color, rightColor: Color, shape: Shape): Modifier =
    clip(shape).halfBackground(leftColor, rightColor)


@Composable
@Preview
private fun Preview_HalfBackground() {
    Box(
        modifier = Modifier
            .size(100.dp, 60.dp)
            .halfBackground(Color.Red, Color.Green)
    )
}