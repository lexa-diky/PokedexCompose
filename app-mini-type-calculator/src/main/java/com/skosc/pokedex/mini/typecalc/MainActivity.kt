package com.skosc.pokedex.mini.typecalc

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import com.skosc.pokedex.core.analytics.FirebaseAnalyticsController
import com.skosc.pokedex.core.analytics.LocalAnalytics
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.domain.settings.SettingsRepository
import com.skosc.pokedex.domain.settings.entity.defaultSettings
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.theme.PokedexTheme
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.compose.LocalDI
import org.kodein.di.instance
import android.view.WindowManager
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected
import com.skosc.pokedex.feature.typedetails.TypeDetailsInit
import com.skosc.pokedex.feature.typedetails.TypeDetailsPage


class MainActivity : ComponentActivity(), DIAware {

    override val di: DI by lazy { (application as DIAware).di }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val analytics = FirebaseAnalyticsController(this)
        val settingsRepository by di.instance<SettingsRepository>()

        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        setContent {
            val settings by settingsRepository.flow.collectAsState(initial = defaultSettings())

            CompositionLocalProvider(
                LocalDI provides di,
                LocalAnalytics provides analytics,
                LocalSettings provides settings
            ) {
                PokedexTheme {
                    Surface(color = LocalColoristic.current.background) {
                        TypeDetailsPage(
                            init = TypeDetailsInit(PokemonTypeExpected.Bug, PokemonTypeExpected.Dragon, true),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 32.dp)
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        }
    }
}
