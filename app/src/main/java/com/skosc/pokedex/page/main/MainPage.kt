package com.skosc.pokedex.page.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.skosc.pokedex.R
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected
import com.skosc.pokedex.enity.domain.SearchResultIcon
import com.skosc.pokedex.enity.ui.BoxCard
import com.skosc.pokedex.enity.ui.BoxCardList
import com.skosc.pokedex.enity.ui.NewsBriefingEntry
import com.skosc.pokedex.enity.ui.sample
import com.skosc.pokedex.feature.pokemondetails.PokemonDetailsDestination
import com.skosc.pokedex.feature.typedetails.TypeDetailsDestination
import com.skosc.pokedex.feature.typedetails.TypeDetailsInit
import com.skosc.pokedex.navigation.LocalNavController
import com.skosc.pokedex.navigation.navigate
import com.skosc.pokedex.newsList
import com.skosc.pokedex.root
import com.skosc.pokedex.settings
import com.skosc.pokedex.uikit.image.CropTransparentTransformation
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.theme.CardShape
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.theme.PokedexTheme
import com.skosc.pokedex.uikit.util.ScreenDimensionTools
import com.skosc.pokedex.uikit.widget.*
import com.skosc.pokedex.widget.*

fun NavGraphBuilder.MainPage() = composable(root.path) {
    val cardBoxViewModel = diViewModel<CardBoxViewModel>()
    val newsBriefingViewModel = diViewModel<NewsBriefingViewModel>()
    val navController = LocalNavController.current

    val cards by cardBoxViewModel.cards.collectAsState()
    val news by newsBriefingViewModel.news.collectAsState()

    InnerMainPage(
        cards = cards,
        news = news,
        onQueryUpdated = { cardBoxViewModel.reload() },
        onSettingsClicked = { navController.navigate(root.settings) },
        onViewAllNewsClick = { navController.navigate(root.newsList) }
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun InnerMainPage(
    cards: BoxCardList,
    news: NewsState,
    onQueryUpdated: (String) -> Unit,
    onSettingsClicked: () -> Unit,
    onViewAllNewsClick: () -> Unit
) {
    RootLayout(stringResource(id = R.string.main_title)) {
        item {
            CardBox(cards, onQueryUpdated)
        }
        item {
            Toolbar()
        }
        item {
            NewsHeader(
                isVisible = news !is NewsState.Error,
                onViewAllClick = onViewAllNewsClick
            )
        }
        if (news is NewsState.Loading || news is NewsState.Error) {
            item {
                Spacer(modifier = Modifier.height(ScreenDimensionTools.screenHeight() / 3))
            }
        }
        if (news is NewsState.Loaded) {
            news.entries.forEach {
                item {
                    NewsBlock(it.title, it.time, it.image, it.url)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(16.dp))
        }
        item {
            Basement(stringResource(id = R.string.main_basement_label_settings), onSettingsClicked)
        }
    }
}

@Composable
private fun CardBox(cards: BoxCardList, onQueryUpdated: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .shadow(16.dp, CardShape)
                .background(LocalColoristic.current.backgroundAccent, CardShape)
        ) {
            SearchField(
                onQueryUpdated = onQueryUpdated,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 32.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            MenuCards(cards)
        }
    }
}

@Composable
private fun MenuCards(cards: BoxCardList) {
    if (cards is BoxCardList.Menu) {
        SmallMenuCards(cards)
    } else if (cards is BoxCardList.SearchResult) {
        SearchMenuCards(cards)
    }
}

@Composable
private fun SearchMenuCards(cards: List<BoxCard.SearchResult>) {
    Column {
        cards.forEachIndexed { idx, card ->
            val isLastItem = cards.size - 1 == idx

            Box(modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable { }
            ) {
                SearchEntry(
                    title = card.title,
                    icon = card.iconPainter,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
            }

            if (!isLastItem) {
                Divider(
                    modifier = Modifier.padding(8.dp)
                )
            } else {
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun Toolbar() {
    val navController = LocalNavController.current

    Surface(
        elevation = 16.dp,
        shape = CardShape,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            ToolbarItem(
                icon = painterResource(id = R.drawable.ic_type_dragon),
                title = stringResource(id = R.string.main_menu_calculator),
                onClick = {
                    navController.navigate(
                        TypeDetailsDestination, args = mapOf(
                            TypeDetailsDestination.ARG_INIT to TypeDetailsInit(
                                primaryType = PokemonTypeExpected.Dragon,
                                secondaryType = null,
                                isEditable = true
                            )
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun ToolbarItem(icon: Painter, title: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(CardShape)
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        PokeSubLabel(text = title)
    }
}

private val BoxCard.SearchResult.iconPainter
    @Composable get() = rememberImagePainter(
        data = when (icon) {
            is SearchResultIcon.Pokemon -> icon.imageUrl
        }
    ) {
        transformations(CropTransparentTransformation())
    }

@Composable
private fun SmallMenuCards(cards: List<BoxCard.Menu>) {
    val navController = LocalNavController.current
    val coloristic = LocalColoristic.current

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(bottom = 32.dp, top = 16.dp)
    ) {
        cards.chunked(2).forEach { items ->
            val left = items[0]
            val right = items.getOrNull(1)

            Row(Modifier.padding(horizontal = 16.dp)) {
                SmallCard(
                    text = left.title.render(),
                    color = coloristic.from(left.color),
                    onClick = { navController.navigate(left.destination) },
                    modifier = Modifier.weight(1f)
                )
                right?.let {
                    Spacer(modifier = Modifier.weight(0.1f))
                    SmallCard(
                        text = it.title.render(),
                        color = coloristic.from(it.color),
                        onClick = { navController.navigate(it.destination) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
private fun NewsHeader(isVisible: Boolean, onViewAllClick: () -> Unit) {
    AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
        ConstraintLayout(
            Modifier
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .fillMaxWidth()
        ) {
            val (titleRef, linkRef) = createRefs()

            SubPokeHeader("Pokémon News",
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
            )

            Box(
                modifier = Modifier
                    .clickable { onViewAllClick() }
                    .padding(horizontal = 8.dp)
                    .constrainAs(linkRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }) {
                PokeLink(
                    text = "View all",
                    modifier = Modifier

                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() = PokedexTheme {
    CompositionLocalProvider(LocalNavController provides rememberNavController()) {
        InnerMainPage(
            BoxCardList.Menu(BoxCard.Menu.sample),
            NewsState.Loaded(NewsBriefingEntry.sample),
            onQueryUpdated = {},
            onSettingsClicked = {},
            onViewAllNewsClick = {}
        )
    }
}