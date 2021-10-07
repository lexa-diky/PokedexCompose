package com.skosc.pokedex.feature.core.details

import androidx.compose.animation.core.tween
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
class GenericDetailsPageScope constructor(
    private val pagerState: PagerState
) {
    val hasPreviousPage: Boolean get() = pagerState.currentPage != 0
    val hasNextPage: Boolean get() = pagerState.currentPage < pagerState.pageCount - 1

    suspend fun scrollToPreviousPage() {
        if (hasPreviousPage) {
            pagerState.animateScrollToPage(pagerState.currentPage - 1, animationSpec = tween(durationMillis = 1_000))
        }
    }

    suspend fun scrollToNextPage() {
        if (hasNextPage) {
            pagerState.animateScrollToPage(pagerState.currentPage + 1, animationSpec = tween(durationMillis = 1_000))
        }
    }
}