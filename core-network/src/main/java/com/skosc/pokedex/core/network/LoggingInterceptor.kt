package com.skosc.pokedex.core.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

object LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.i("PDXOkHttp3", "${request.method}: ${request.url}")

        return chain.proceed(request)
    }
}