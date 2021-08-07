package com.skosc.pokedex.repository

import com.skosc.pokedex.enity.domain.MenuEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MenuRepository {

    suspend fun getAvailableMenuItems(): List<MenuEntry> {
        return MenuEntry.values().toList()
    }
}