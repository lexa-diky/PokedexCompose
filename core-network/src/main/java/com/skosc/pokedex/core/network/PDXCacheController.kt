package com.skosc.pokedex.core.network

import io.ktor.client.request.*

class PDXCacheController {

    object Header {

        const val CACHE_MODE = "PDX-CACHE-MODE"
    }

    object Modes {

        const val HEAVY = "HEAVY"
    }
}

fun HttpRequestBuilder.heavyCache() {
    header(PDXCacheController.Header.CACHE_MODE, PDXCacheController.Modes.HEAVY)
}