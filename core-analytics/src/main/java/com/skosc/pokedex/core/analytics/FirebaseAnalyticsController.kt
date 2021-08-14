package com.skosc.pokedex.core.analytics

import android.content.Context
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsController(private val context: Context) : AnalyticsController {

    private val firebase by lazy { FirebaseAnalytics.getInstance(context) }

    override fun sendEvent(tag: String, parameters: Map<String, String>) {
        val parametersArray = parameters.map { it.key to it.value }.toTypedArray()
        firebase.logEvent(tag, bundleOf(*parametersArray))
    }
}