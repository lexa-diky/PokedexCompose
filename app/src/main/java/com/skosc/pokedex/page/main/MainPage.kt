package com.skosc.pokedex.page.main

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.skosc.pokedex.enity.domain.SearchResultIcon
import com.skosc.pokedex.enity.ui.BoxCard
import com.skosc.pokedex.enity.ui.BoxCardList
import com.skosc.pokedex.enity.ui.NewsBriefingEntry
import com.skosc.pokedex.enity.ui.sample
import com.skosc.pokedex.navigation.LocalNavController
import com.skosc.pokedex.navigation.navigate
import com.skosc.pokedex.newsList
import com.skosc.pokedex.root
import com.skosc.pokedex.uikit.image.CropTransparentTransformation
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.theme.CardShape
import com.skosc.pokedex.uikit.theme.UIColor
import com.skosc.pokedex.uikit.widget.*
import com.skosc.pokedex.widget.*

fun NavGraphBuilder.MainPage() = composable(root.path) {
    val cardBoxViewModel = diViewModel<CardBoxViewModel>()
    val newsBriefingViewModel = diViewModel<NewsBriefingViewModel>()

    val cards by cardBoxViewModel.cards.collectAsState()
    val news by newsBriefingViewModel.news.collectAsState()

    InnerMainPage(
        cards = cards,
        news = news,
        onQueryUpdated = { cardBoxViewModel.onQueryUpdateUpdated(it) }
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun InnerMainPage(
    cards: BoxCardList,
    news: List<NewsBriefingEntry>,
    onQueryUpdated: (String) -> Unit
) {
    RootLayout("Pokédex") {
        item {
            CardBox(cards, onQueryUpdated)
        }
        item {
            NewsHeader()
        }
        news.forEach {
            item {
                NewsBlock(it.title, it.time, it.image, it.url)
            }
        }
        item {
            Spacer(modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
private fun CardBox(cards: BoxCardList, onQueryUpdated: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .background(UIColor.BackgroundAccent, CardShape)
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

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(bottom = 32.dp, top = 16.dp)
    ) {
        cards.chunked(2).forEach { items ->
            val left = items[0]
            val right = items.getOrNull(1)

            Row(Modifier.padding(horizontal = 16.dp)) {
                SmallCard(
                    text = left.title,
                    color = left.color,
                    onClick = { navController.navigate(left.destination) },
                    modifier = Modifier.weight(1f)
                )
                right?.let {
                    Spacer(modifier = Modifier.weight(0.1f))
                    SmallCard(
                        text = it.title,
                        color = it.color,
                        onClick = { navController.navigate(it.destination) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun NewsHeader() {
    ConstraintLayout(
        Modifier
            .padding(vertical = 8.dp, horizontal = 32.dp)
            .fillMaxWidth()
    ) {
        val navController = LocalNavController.current
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
                .clickable { navController.navigate(root.newsList) }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompositionLocalProvider(LocalNavController provides rememberNavController()) {
        InnerMainPage(BoxCardList.Menu(BoxCard.Menu.sample), NewsBriefingEntry.sample) {

        }
    }
}