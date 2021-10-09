package com.skosc.pokedex

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import com.skosc.pokedex.core.analytics.FirebaseAnalyticsController
import com.skosc.pokedex.core.analytics.LocalAnalytics
import com.skosc.pokedex.domain.ignition.IgnitionController
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.domain.settings.SettingsRepository
import com.skosc.pokedex.domain.settings.entity.defaultSettings
import com.skosc.pokedex.page.RootPage
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.theme.PokedexTheme
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.compose.LocalDI
import org.kodein.di.instance
import android.view.WindowManager





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
                // launchIgnition()

                PokedexTheme {
                    Surface(color = LocalColoristic.current.background) {
                        RootPage()
                    }
                }
            }
        }
    }

    @Composable
    @SuppressLint("ComposableNaming")
    private fun launchIgnition() {
        val ignitionController by LocalDI.current.di.instance<IgnitionController>()

        LaunchedEffect(Unit) {
            ignitionController.ignite()
        }
    }
}
