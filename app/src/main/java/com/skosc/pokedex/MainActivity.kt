package com.skosc.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import com.skosc.pokedex.page.RootPage
import com.skosc.pokedex.page.main.MainPage
import com.skosc.pokedex.uikit.theme.PokedexTheme
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.compose.LocalDI

class MainActivity : ComponentActivity(), DIAware {

    override val di: DI by lazy { (application as DIAware).di }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalDI provides di) {
                PokedexTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        RootPage()
                    }
                }
            }
        }
    }
}
