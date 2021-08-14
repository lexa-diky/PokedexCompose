package com.skosc.pokedex.uikit.animation

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import androidx.compose.runtime.*

@Composable
fun animateRotation(timeInterpolator: TimeInterpolator): Float {
    var rotation: Float by remember { mutableStateOf(0f) }
    var animator: ValueAnimator? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        animator = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 5_000
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            startDelay = 0
            interpolator = timeInterpolator
            start()
            addUpdateListener {
                rotation = it.animatedValue as Float
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            animator?.cancel()
        }
    }

    return rotation
}