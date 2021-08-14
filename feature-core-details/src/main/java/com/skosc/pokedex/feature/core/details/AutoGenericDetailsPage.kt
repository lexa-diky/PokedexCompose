package com.skosc.pokedex.feature.core.details

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
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
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.image.CropTransparentTransformation
import com.skosc.pokedex.uikit.localViewModel
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.theme.UIColor
import com.skosc.pokedex.uikit.widget.OrderText
import com.skosc.pokedex.uikit.widget.RotatingPokeBall
import com.skosc.pokedex.uikit.widget.SubPokeHeader
import com.skosc.pokedex.uikit.widget.TypeChip
import kotlin.math.abs

@Composable
fun <T> GenericDetailsPage(spec: GenericDetailsSpec<T>) {
    val viewModel = localViewModel { GenericDetailsViewModel(spec) }

    val details by viewModel.details.collectAsState()
    if (details != null) {
        GenericDetailsPage(details = details!!)
    }
}
