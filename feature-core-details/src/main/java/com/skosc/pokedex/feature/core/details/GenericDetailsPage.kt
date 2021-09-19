package com.skosc.pokedex.feature.core.details

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skosc.pokedex.feature.core.details.entity.BaseDetailsItem
import com.skosc.pokedex.feature.core.details.entity.DetailsHeaderItem
import com.skosc.pokedex.feature.core.details.entity.DetailsPageItem
import com.skosc.pokedex.feature.core.details.entity.TabRowItem
import com.skosc.pokedex.uikit.image.CropTransparentTransformation
import com.skosc.pokedex.uikit.theme.UIColor
import com.skosc.pokedex.uikit.widget.OrderText
import com.skosc.pokedex.uikit.widget.RotatingPokeBall
import com.skosc.pokedex.uikit.widget.SubPokeHeader
import com.skosc.pokedex.uikit.widget.TypeChip
import kotlin.math.abs

private val ITEM_HEADER = "__ITEM_HEADER"
private val ITEM_POKE_BALL = "__ITEM_POKEBALL"
private val ITEM_CONTENT_SHEET = "__ITEM_CONTENT_SHEET"

@Composable
fun GenericDetailsPage(details: BaseDetailsItem) {
    Box(modifier = Modifier.background(UIColor.Accent.SoftSwampGreen).fillMaxSize()) {
        val lazyListState = rememberLazyListState()

        LazyColumn(
            state = lazyListState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item(ITEM_HEADER) {
                DetailsHeader(
                    order = details.header.order,
                    title = details.header.title,
                    tags = details.header.tags,
                    modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp)
                )
            }

            val pokeballOffset =
                lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == ITEM_POKE_BALL }?.offset
                    ?: -10000000
            val sheetOffset =
                lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == ITEM_CONTENT_SHEET }?.offset
                    ?: -10000000
            val headerVisibilityInfo =
                lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == ITEM_HEADER }

            item(ITEM_POKE_BALL) {
                val relation = abs(pokeballOffset).toFloat() / abs(sheetOffset).toFloat()
                val alpha by animateFloatAsState(if (relation > 0.3f && headerVisibilityInfo == null) 0f else 1f)

                PokemonImage(
                    imageUrl = details.header.image,
                    modifier = Modifier
                        .size(200.dp)
                        .zIndex(1f)
                        .alpha(alpha)
                )
            }

            item(ITEM_CONTENT_SHEET) {
                BottomSheetImpl(
                    details.pages,
                    modifier = Modifier
                        .offset(y = -52.dp)
                        .animateContentSize()
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
private fun PokemonImage(imageUrl: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {

        RotatingPokeBall(
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = rememberImagePainter(data = imageUrl) {
                transformations(CropTransparentTransformation())
                fadeIn()
            },
            contentDescription = null,
            modifier = Modifier.fillMaxSize(0.7f)
        )
    }
}

@Composable
private fun DetailsHeader(
    order: Int,
    title: String,
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Column {
            SubPokeHeader(text = title, color = Color.White)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                tags.forEach { tag ->
                    TypeChip(type = tag)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OrderText(order, color = UIColor.BackgroundAccent)
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
private fun BottomSheetImpl(pages: List<DetailsPageItem>, modifier: Modifier = Modifier) {
    if (pages.isEmpty()) {
        return
    }

    DetailsBottomSheet(modifier = modifier) {
        val titles = pages.map { it.title }

        val pagerState = rememberPagerState(pageCount = pages.size)
        var selectedTabItem by remember { mutableStateOf(pages.first().title) }

        LaunchedEffect(selectedTabItem) {
            pagerState.scrollToPage(titles.indexOf(selectedTabItem))
        }

        Column {
            TabRow(
                rowItems = titles,
                selectedItem = selectedTabItem,
                onItemSelected = { item -> selectedTabItem = item },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp)
            )

            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { page ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    pages[page].content()
                }
            }
        }
    }
}

@Composable
private fun TabRow(
    rowItems: List<TabRowItem>,
    selectedItem: TabRowItem,
    onItemSelected: (TabRowItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
        modifier = modifier
    ) {
        items(rowItems) { item ->
            val isItemSelected = item == selectedItem
            val bottomLineColor by animateColorAsState(if (isItemSelected) UIColor.ShadowBlack else Color.Transparent)

            var textLayout: TextLayoutResult? by remember { mutableStateOf(null) }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onItemSelected(item) }
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.SemiBold,
                    onTextLayout = { textLayout = it }
                )
                textLayout?.let { layout ->
                    Box(modifier = Modifier
                        .background(bottomLineColor)
                        .height(2.dp)
                        .width(
                            with(LocalDensity.current) {
                                layout.size.width.toDp() + 32.dp
                            }
                        )
                    )


                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
private fun DetailsBottomSheet(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier.background(
            UIColor.BackgroundAccent,
            RoundedCornerShape(32.dp)
        )
    ) {
        content()
    }
}

@Composable
@Preview(name = "Generics Details Page", showBackground = true, showSystemUi = true)
fun Preview_GenericDetailsPage() {
    GenericDetailsPage(BaseDetailsItem(
        header = DetailsHeaderItem(
            title = "Bulbasaur",
            order = 1,
            tags = listOf("Grass", "Normal"),
            image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        ),
        pages = listOf(
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
