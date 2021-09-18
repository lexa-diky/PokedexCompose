package com.skosc.pokedex.domain.settings

import com.skosc.pokedex.core.localization.Localization
import com.skosc.pokedex.domain.settings.entity.PokeAppSettings

object AppSettingsMapper {

    fun toProtobuf(settings: PokeAppSettings): AppSettingsProtobuf {
        return AppSettingsProtobuf.newBuilder()
            .setLocalization(settings.localization.name)
            .build()
    }

    fun fromProtobuf(settings: AppSettingsProtobuf): PokeAppSettings {
        val localization = settings.localization
            ?.takeIf { it.isNotBlank() }
            ?.let(Localization::valueOf)
            ?: Localization.System

        return PokeAppSettings(
            localization = localization
        )
    }
}