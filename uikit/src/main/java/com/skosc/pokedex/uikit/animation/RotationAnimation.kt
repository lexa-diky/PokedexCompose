package com.skosc.pokedex.uikit.animation

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun animateRotation(interpolator: TimeInterpolator): Float {
    val rotation = remember { mutableStateOf(0f) }
    LaunchedEffect(rotation) {
        ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 5_000
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            this.interpolator = interpolator
            start()
            addUpdateListener {
                rotation.value = it.animatedValue as Float
            }
        }
    }
    return rotation.value
}