package com.skosc.pokedex.uikit.widget

import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.animation.animateRotation
import com.skosc.pokedex.uikit.theme.ShadowWitheBackground

@Composable
fun BoxScope.RotatingPokeBall(modifier: Modifier = Modifier) {
    val rotation = animateRotation(OvershootInterpolator())

    Image(
        painter = painterResource(id = R.drawable.ic_pokeball_filed),
        contentDescription = null,
        colorFilter = ColorFilter.tint(ShadowWitheBackground),
        modifier = modifier.rotate(rotation)
    )
}