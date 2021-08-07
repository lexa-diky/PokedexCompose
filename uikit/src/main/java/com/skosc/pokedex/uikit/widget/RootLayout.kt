package com.skosc.pokedex.uikit.widget

import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.theme.ShadowWitheBackground
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.animation.animateRotation

private const val HEADER_KEY = "__ROOT_LAYOUT_HEADER_KEY"

@Composable
fun RootLayout(header: String, items: LazyListScope.() -> Unit) {
    val rotation = animateRotation(OvershootInterpolator())

    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_pokeball_filed),
            contentDescription = null,
            colorFilter = ColorFilter.tint(ShadowWitheBackground),
            modifier = Modifier
                .size(200.dp, 200.dp)
                .align(Alignment.TopEnd)
                .offset(64.dp, (-64).dp)
                .rotate(rotation)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()

        ) {
            item(HEADER_KEY) {
                PokeHeader(header, modifier = Modifier.padding(start = 32.dp, top = 32.dp))
            }
            items()
        }
    }
}