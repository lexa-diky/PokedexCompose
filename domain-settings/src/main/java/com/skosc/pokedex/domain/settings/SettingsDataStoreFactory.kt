package com.skosc.pokedex.domain.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile

class SettingsDataStoreFactory(private val context: Context) {

    fun create(): DataStore<AppSettingsProtobuf> {
        return DataStoreFactory.create(
            serializer = AppSettingsSerializer,
            produceFile = { context.dataStoreFile("app_settings") }
        )
    }
}