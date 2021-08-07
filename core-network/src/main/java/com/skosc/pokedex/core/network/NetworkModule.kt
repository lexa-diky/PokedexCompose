package com.skosc.pokedex.core.network

import android.content.Context
import com.skosc.pokedex.core.di.PokeModule
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json
import okhttp3.Cache
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import java.io.File

val NetworkModule = PokeModule("network") {
    bind<HttpClient>() with singleton {
        createConfiguredClient(instance())
    }
}

private fun createConfiguredClient(context: Context) = HttpClient(OkHttp) {
    engine {
        config {
            cache(
                Cache(
                    directory = File(context.cacheDir, "http_cache"),
                    maxSize = 50L * 1024L * 1024L
                )
            )

            addInterceptor(HeavyCacheInterceptor())
            addInterceptor(LoggingInterceptor)
        }
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        })
    }
}