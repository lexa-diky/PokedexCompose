package com.skosc.pokedex.uikitdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.R
import com.skosc.pokedex.uikit.theme.PokedexTheme
import com.skosc.pokedex.uikit.widget.filter.*

class UikitDemoActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                FilterLayout(
                    modifier = Modifier.padding(16.dp)
                ) {
                    repeat(4) {
                        FilterTagContainer {
                            FieldFilterTag(default = "hello world", placeholder = "Name")
                        }
                    }
                    repeat(3) {
                        FilterTagContainer {
                            ToggleFilterTag(text = "Toggle Me")
                        }
                    }

                    FilterTagContainer {
                        SelectionFilterTag(
                            options = listOf(
                                SelectionTagOption(1, "Grass", painterResource(R.drawable.ic_pokeball)),
                                SelectionTagOption(1, "Fire", painterResource(R.drawable.ic_pokeball)),
                                SelectionTagOption(1, "Dragon", painterResource(R.drawable.ic_pokeball)),
                                SelectionTagOption(1, "Fairy", painterResource(R.drawable.ic_pokeball)),
                                SelectionTagOption(1, "Nothing"),
                            ),
                            placeholder = "Pokemon Type"
                        )
                    }
                }
            }
        }
    }
}
