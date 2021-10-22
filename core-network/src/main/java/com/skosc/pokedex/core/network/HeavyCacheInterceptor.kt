package com.skosc.pokedex.core.network

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

internal class HeavyCacheInterceptor : Interceptor {

    private val heavyCacheControl: CacheControl by lazy {
        CacheControl.Builder()
            .maxAge(HEAVY_CACHE_TIME_DAYS, TimeUnit.DAYS)
            .build()

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val cacheModeHeader = request.header(PDXCacheController.Header.CACHE_MODE)

        return cacheModeHeader?.let { proceedWithHeavyCache(chain) }
            ?: chain.proceed(request)
    }

    private fun proceedWithHeavyCache(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val headers = request.headers.newBuilder()
            .removeAll(PDXCacheController.Header.CACHE_MODE)
            .build()

        return request.newBuilder()
            .headers(headers)
            .cacheControl(
                CacheControl.Builder()
                    .maxAge(HEAVY_CACHE_TIME_DAYS, TimeUnit.DAYS)
                    .build()
            )
            .build()
            .let(chain::proceed)
    }

    companion object {

        private const val HEAVY_CACHE_TIME_DAYS = 7
    }
}