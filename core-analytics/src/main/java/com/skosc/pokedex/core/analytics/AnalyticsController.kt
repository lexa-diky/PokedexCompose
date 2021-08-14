package com.skosc.pokedex.core.analytics

interface AnalyticsController {

    fun sendEvent(tag: String, parameters: Map<String, String>)
}