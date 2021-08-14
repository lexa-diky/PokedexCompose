package com.skosc.pokedex.core.analytics

import android.util.Log

class StubAnalyticsController : AnalyticsController {

    override fun sendEvent(tag: String, parameters: Map<String, String>) {
        Log.v("analytics", parameters.toString())
    }
}