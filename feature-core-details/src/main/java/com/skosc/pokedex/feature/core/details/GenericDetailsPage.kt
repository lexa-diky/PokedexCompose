package com.skosc.pokedex.feature.core.details

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skosc.pokedex.feature.core.details.entity.*
import com.skosc.pokedex.uikit.image.CropTransparentTransformation
import com.skosc.pokedex.uikit.modifier.halfBackground
import com.skosc.pokedex.uikit.theme.ColorDef
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.widget.*
import kotlinx.coroutines.launch
import kotlin.random.Random

private val ITEM_HEADER = "__ITEM_HEADER"
private val ITEM_POKE_BALL = "__ITEM_POKEBALL"
private val ITEM_CONTENT_SHEET = "__ITEM_CONTENT_SHEET"

@Composable
fun GenericDetailsPageScope.GenericDetailsPage(details: BaseDetailsItem) {
    val coloristic = LocalColoristic.current

    Box(
        modifier = Modifier
            .background(coloristic.from(details.background.base))
            .halfBackground(
                coloristic.from(details.background.left),
                coloristic.from(details.background.right),
                RectangleShape
            )
            .fillMaxSize()
    ) {
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


            item(ITEM_POKE_BALL) {

                PokemonImage(
                    imageUrl = details.header.image,
                    modifier = Modifier
                        .size(200.dp)
                        .zIndex(1f)
                )
            }

            item(ITEM_CONTENT_SHEET) {
                BottomSheetImpl(
                    details.pages,
                    modifier = Modifier
                        .offset(y = -52.dp)
                        .animateContentSize()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
private fun GenericDetailsPageScope.PokemonImage(imageUrl: String, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()

    var xDiff by remember { mutableStateOf(0.dp) }
    var yDiff by remember { mutableStateOf(0.dp) }

    fun launchJumpAnimation() {
        xDiff += (-Random.nextInt(-8, 8))
            .coerceAtLeast(-8)
            .coerceAtMost(8)
            .dp
        yDiff += (-Random.nextInt(-8, 8))
            .coerceAtLeast(-8)
            .coerceAtMost(8)
            .dp
    }

    val xAnimated by animateDpAsState(
        targetValue = xDiff,
        animationSpec = spring(),
        finishedListener = {
            xDiff = 0.dp
        })
    val yAnimated by animateDpAsState(
        targetValue = yDiff,
        animationSpec = spring(),
        finishedListener = { yDiff = 0.dp }
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {

        RotatingPokeBall(
            modifier = Modifier.fillMaxSize()
                .zIndex(-1f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (hasPreviousPage) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { scope.launch { scrollToPreviousPage() } }
                )
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
            Image(
                painter = rememberImagePainter(data = imageUrl) {
                    transformations(CropTransparentTransformation())
                    crossfade(true)
                },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(0.7f)
                    .offset(x = xAnimated, y = yAnimated)
                    .clickable(
                        role = Role.Image,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { launchJumpAnimation() }
                    )
            )
            if (hasNextPage) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { scope.launch { scrollToNextPage() } }
                )
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
        }
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
            SubPokeHeader(text = title, secondary = true)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                tags.forEach { tag ->
                    TypeChip(type = tag)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OrderText(order)
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
private fun BottomSheetImpl(pages: List<DetailsPageItem>, modifier: Modifier = Modifier) {
    if (pages.isEmpty()) {
        return
    }

    DetailsBottomSheet(modifier = modifier.zIndex(1f)) {
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
    val coloristic = LocalColoristic.current

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
        modifier = modifier
    ) {
        items(rowItems) { item ->
            val isItemSelected = item == selectedItem
            val bottomLineColor by animateColorAsState(if (isItemSelected) coloristic.accentShadow else Color.Transparent)

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
                    color = LocalColoristic.current.textPrimaryAccent,
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
            LocalColoristic.current.backgroundAccent,
            RoundedCornerShape(32.dp)
        )
    ) {
        content()
    }
}

@Composable
@Preview(name = "Generics Details Page", showBackground = true, showSystemUi = true)
@OptIn(ExperimentalPagerApi::class)
private fun Preview_GenericDetailsPage() {
    GenericDetailsPageScope(rememberPagerState(pageCount = 1)).GenericDetailsPage(BaseDetailsItem(
        background = DetailsBackground(ColorDef.TypeFairy, ColorDef.TypeFairy, ColorDef.TypeFairy),
        header = DetailsHeaderItem(
            title = "Bulbasaur",
            order = 1,
            tags = listOf("Grass", "Normal"),
            image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        ),
        pages = listOf(
            DetailsPageItem(
                title = TabRowItem("About"),
                content = { Text("About Content") },
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

            ))
    )
}
