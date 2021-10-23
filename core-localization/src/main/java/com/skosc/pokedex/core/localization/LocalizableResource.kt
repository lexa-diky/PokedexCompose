package com.skosc.pokedex.core.localization

interface LocalizableResource {

    fun getLocalized(localization: Localization): String
}

interface LocalizableResourceGroup<T> {

    fun getLocalized(localization: Localization): T
}
