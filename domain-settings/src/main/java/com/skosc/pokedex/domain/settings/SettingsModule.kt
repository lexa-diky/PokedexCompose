package com.skosc.pokedex.domain.settings

import androidx.datastore.core.DataStore
import com.skosc.pokedex.core.di.PokeModule
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val SettingsDomainModule = PokeModule("settings") {
    bind<SettingsDataStoreFactory>() with singleton { SettingsDataStoreFactory(instance()) }
    bind<DataStore<AppSettingsProtobuf>>() with singleton { instance<SettingsDataStoreFactory>().create() }
    bind<SettingsRepository>() with singleton { SettingsRepository(instance()) }
}
