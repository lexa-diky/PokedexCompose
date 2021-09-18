package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skosc.pokedex.uikit.R

data class EvolutionChainItem(
    val name: String,
    val icon: Painter
)

@Composable
fun EvolutionChain(items: List<EvolutionChainItem>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.wrapContentHeight()
    ) {
        items.forEachIndexed {  idx, item ->
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = item.icon,
                        contentDescription = item.name,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = item.name, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }

            }
            if (idx != items.lastIndex) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun Preview_EvolutionChain() {
    EvolutionChain(
        items = listOf(
            EvolutionChainItem("Pokemon 1", painterResource(id = R.drawable.ic_pokeball_filed)),
            EvolutionChainItem("Pokemon 2", painterResource(id = R.drawable.ic_pokeball)),
            EvolutionChainItem("Pokemon 1", painterResource(id = R.drawable.ic_pokeball_filed)),
            EvolutionChainItem("Pokemon 2", painterResource(id = R.drawable.ic_pokeball))
        )
    )
}
