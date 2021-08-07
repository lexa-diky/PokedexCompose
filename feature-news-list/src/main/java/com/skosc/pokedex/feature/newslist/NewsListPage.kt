package com.skosc.pokedex.feature.newslist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.widget.NewsBlock
import com.skosc.pokedex.uikit.widget.RootLayout

fun NavGraphBuilder.NewsListPage() = composable(NewsListDestination.path) {
    val viewModel: NewsListViewModel = diViewModel()

    val news by viewModel.news.collectAsState(emptyList())

    RootLayout("News") {
        news.forEach {
            item { Spacer(modifier = Modifier.size(16.dp)) }
            item {
                NewsBlock(title = it.title, time = it.time, image = it.image, url = it.url)
            }
            item { Spacer(modifier = Modifier.size(16.dp)) }
        }
    }
}