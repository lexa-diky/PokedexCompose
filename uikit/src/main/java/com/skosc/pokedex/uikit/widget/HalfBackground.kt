package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class HalfSplitBackground(
    private val leftColor: Color,
    private val rightColor: Color
) : DrawModifier {

    override fun ContentDrawScope.draw() {
        val canvasSize = size
        val canvasWidth = size.width
        val canvasHeight = size.height

        rotate(45f) {
            drawRect(
                color = leftColor,
                topLeft = Offset(x = -canvasWidth / 2F, y = canvasHeight / 2F),
                size = canvasSize * 2F
            )
        }
        rotate(-135f) {
            drawRect(
                color = rightColor,
                topLeft = Offset(x = -canvasWidth / 2F - 1f, y = canvasHeight / 2F - 1f),
                size = canvasSize * 2F
            )
        }

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
            .size(100.dp)
            .halfBackground(Color.Red, Color.Green, RoundedCornerShape(8.dp))
    )
}