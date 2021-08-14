package com.skosc.pokedex

import androidx.compose.runtime.Composable
import com.skosc.pokedex.core.analytics.AnalyticsController
import com.skosc.pokedex.core.analytics.rememberAnalytics

internal class AnalyticsSpec(private val controller: AnalyticsController) {

    fun onNavigation(destination: String) {
        controller.sendEvent("NAVIGATION", mapOf(
            "destination" to destination
        ))
    }
}

@Composable
internal fun rememberLocalAnalyticsSpec(): AnalyticsSpec {
    val analytics = rememberAnalytics()
    return AnalyticsSpec(analytics)
}

