package com.skosc.pokedex.feature.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.theme.PokeColor
import com.skosc.pokedex.uikit.widget.SettingsCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.SettingsPage() = composable(SettingsDestination.path) {
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    var bottomSheetContent by remember {
        mutableStateOf<@Composable () -> Unit>(value = { Text(text = "PLACEHOLDER") })
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetElevation = 16.dp,
        sheetContent = {
            bottomSheetContent()
        }, content = {
            SettingsPageContent(
                onCloseBottomSheet = {
                  coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
                },
                onPageSelected = {
                    bottomSheetContent = it
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    }
                })
        })
}

@Composable
private fun SettingsPageContent(
    onPageSelected: (@Composable () -> Unit) -> Unit,
    onCloseBottomSheet: () -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        LanguageSettingsCard(onPageSelected = onPageSelected, onCloseBottomSheet = onCloseBottomSheet)
        ResetAndSupportDevCard()
    }
}

private fun LazyListScope.ResetAndSupportDevCard() = item("__RESET_SUPPORT_DEV") {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SettingsCard(
            title = "Reset",
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_reset),
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = "Language",
                    modifier = Modifier.size(32.dp) // TODO make dynamic
                )
            },
            backgroundColor = PokeColor.Red,
            modifier = Modifier
                .wrapContentHeight()
        )

        SettingsCard(
            title = "Support Dev",
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_developer),
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = "Language",
                    modifier = Modifier.size(32.dp) // TODO make dynamic
                )
            },
            backgroundColor = PokeColor.Green,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        )
    }
}

private fun LazyListScope.LanguageSettingsCard(
    onPageSelected: (@Composable () -> Unit) -> Unit,
    onCloseBottomSheet: () -> Unit,
) = item("__ITEM_SETTINGS_LANGUAGE") {
    val settings = LocalSettings.current

    SettingsCard(
        title = "Language",
        subtitle = settings.localization.name,
        icon = {
            Image(
                painter = painterResource(id = R.drawable.ic_language),
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "Language",
                modifier = Modifier.size(32.dp) // TODO make dynamic
            )
        },
        backgroundColor = PokeColor.Teal,
        onClick = {
            onPageSelected {
                LanguageSettingsPage(onFinished = onCloseBottomSheet)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}
