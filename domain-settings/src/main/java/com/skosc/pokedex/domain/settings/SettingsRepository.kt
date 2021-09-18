package com.skosc.pokedex.domain.settings

import androidx.datastore.core.DataStore
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings
import kotlinx.coroutines.flow.map

class SettingsRepository(
    private val dataStore: DataStore<AppSettingsProtobuf>
) {

    val flow = dataStore.data
        .map(AppSettingsMapper::fromProtobuf)


    @Synchronized
    suspend fun update(settings: PokeAppSettings) {
        dataStore.updateData {
            AppSettingsMapper.toProtobuf(settings)
        }
    }

    @Synchronized
    suspend fun update(updater: (PokeAppSettings) -> PokeAppSettings) {
        dataStore.updateData { protoSettings ->
            val currentSettings = AppSettingsMapper.fromProtobuf(protoSettings)
            val updatedSettings = updater(currentSettings)
            AppSettingsMapper.toProtobuf(updatedSettings)
        }
    }
}
