package com.skosc.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.skosc.pokedex.feature.core.details.GenericDetailsPage
import com.skosc.pokedex.feature.core.details.entity.BaseDetailsItem
import com.skosc.pokedex.feature.core.details.entity.DetailsPageItem
import com.skosc.pokedex.feature.core.details.entity.TabRowItem
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
                        TestDetailsPage()
                    }
                }
            }
        }
    }
}

@Composable
private fun TestDetailsPage() {
    GenericDetailsPage(BaseDetailsItem(listOf(
        DetailsPageItem(
            title = TabRowItem("About"),
            content = { Text("About Content") }
        ),
        DetailsPageItem(
            title = TabRowItem("Stats"),
            content = { Text("Stats Content") }
        ),
        DetailsPageItem(
            title = TabRowItem("Evolution"),
            content = { Text("Evolution Content") }
        ),
        DetailsPageItem(
            title = TabRowItem("Search"),
            content = { Text("Search Content") }
        ),
    )))
}