package com.skosc.pokedex.uikit.modifier

import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha

fun Modifier.shimmer(enabled: Boolean) = composed {
    val target = if (enabled) 0.6f else 1f
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = target,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing, delayMillis = 200),
            repeatMode = RepeatMode.Reverse
        )
    )
    alpha(alpha)
}