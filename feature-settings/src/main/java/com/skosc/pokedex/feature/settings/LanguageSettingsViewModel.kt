package com.skosc.pokedex.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skosc.pokedex.core.localization.Localization
import com.skosc.pokedex.domain.settings.SettingsRepository
import kotlinx.coroutines.launch

class LanguageSettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    fun updateLanguage(localization: Localization) {
        viewModelScope.launch {
            settingsRepository.update { settings ->
                settings.copy(localization = localization)
            }
        }
    }
}