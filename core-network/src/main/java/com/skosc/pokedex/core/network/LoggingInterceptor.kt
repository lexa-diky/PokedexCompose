package com.skosc.pokedex.core.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

object LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Timber.i(request.method + ": " + request.url)
        return chain.proceed(request)
    }
}