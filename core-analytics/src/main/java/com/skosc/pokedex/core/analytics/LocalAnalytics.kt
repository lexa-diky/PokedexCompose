package com.skosc.pokedex.core.analytics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalAnalytics: ProvidableCompositionLocal<AnalyticsController> = compositionLocalOf { StubAnalyticsController() }

@Composable
fun rememberAnalytics(): AnalyticsController = LocalAnalytics.current
