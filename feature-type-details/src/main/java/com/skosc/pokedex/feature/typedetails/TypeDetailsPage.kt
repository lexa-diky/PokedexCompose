package com.skosc.pokedex.feature.typedetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.skosc.pokedex.core.util.applyIf
import com.skosc.pokedex.domain.pokemon.entity.PokemonTypeExpected
import com.skosc.pokedex.domain.pokemon.entity.PokemonWeaknessRelation
import com.skosc.pokedex.domain.settings.LocalSettings
import com.skosc.pokedex.feature.core.pokemon.icon
import com.skosc.pokedex.navigation.RouteComposable
import com.skosc.pokedex.uikit.coloristics.ColorPicker
import com.skosc.pokedex.uikit.diViewModel
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.widget.*

val TypeDetailsPage = RouteComposable<TypeDetailsInit>("/type/details") { init ->
    TypeDetailsPage(init)
}


@Composable
@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
private fun TypeDetailsPage(init: TypeDetailsInit, modifier: Modifier = Modifier) {
    val viewModel = diViewModel<TypeDetailsBottomSheetViewModel, TypeDetailsInit>(arg = init)
    val typeInfo by viewModel.typeInfo.collectAsState()
    AnimatedVisibility(visible = typeInfo != null, enter = expandVertically()) {
        TypeDetailsPageInner(typeInfo!!, modifier) { event ->
            viewModel.onEvent(event)
        }
    }
}


@Composable
private fun TypeDetailsPageInner(state: TypeDetailsState, modifier: Modifier = Modifier, onEvent: (TypeDetailsEvent) -> Unit) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .then(modifier)
    ) {
        Header(
            state = state,
            modifier = Modifier
                .applyIf(state.isEditable) { clickable { onEvent(TypeDetailsEvent.ShowTypeSelectDialog) } }
                .padding(8.dp)
        )

        TypeSelectionDialog(
            state = state,
            onTypesSelected = { onEvent(TypeDetailsEvent.TypesSelected(it)) },
            onDismiss = { onEvent(TypeDetailsEvent.DismissSelection) }
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            TypeDetailsSection(
                stringResource(id = R.string.type_details_good_damamge),
                state.relations,
                setOf(PokemonWeaknessRelation.Magnitude.DOUBLE_TO, PokemonWeaknessRelation.Magnitude.QUAD_TO)
            )
            TypeDetailsSection(
                stringResource(id = R.string.type_details_good_defence),
                state.relations,
                setOf(PokemonWeaknessRelation.Magnitude.HALF_FROM, PokemonWeaknessRelation.Magnitude.QUARTER_FROM, PokemonWeaknessRelation.Magnitude.NO_FROM)
            )
            TypeDetailsSection(
                stringResource(id = R.string.type_details_bad_damamge),
                state.relations,
                setOf(PokemonWeaknessRelation.Magnitude.HALF_TO, PokemonWeaknessRelation.Magnitude.NO_TO, PokemonWeaknessRelation.Magnitude.QUARTER_TO)
            )
            TypeDetailsSection(
                stringResource(id = R.string.type_details_bad_defence),
                state.relations,
                setOf(PokemonWeaknessRelation.Magnitude.DOUBLE_FROM, PokemonWeaknessRelation.Magnitude.QUARTER_FROM)
            )
        }

        Spacer(Modifier.size(32.dp))
    }
}

@Composable
private fun Header(state: TypeDetailsState, modifier: Modifier = Modifier) {
    val settings = LocalSettings.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = state.primaryType.expected.icon(),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        if (state.secondaryType != null) {
            Image(
                painter = state.secondaryType.expected.icon(),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        if (state.secondaryType == null) {
            SubPokeHeader(text = state.primaryType.getLocalized(settings.localization))
        } else {
            SubPokeHeader(
                text = stringResource(
                    id = R.string.type_details_title_mixed,
                    state.primaryType.getLocalized(settings.localization),
                    state.secondaryType.getLocalized(settings.localization)
                )
            )
        }
    }
}

@Composable
private fun ColumnScope.TypeDetailsSection(title: String, relations: List<PokemonWeaknessRelation>, magnitude: Set<PokemonWeaknessRelation.Magnitude>) {
    val coloristic = LocalColoristic.current

    ParagraphPokeHeader(text = title)
    FlowRow(mainAxisSpacing = 8.dp, crossAxisSpacing = 8.dp) {
        relations.filter { it.magnitude in magnitude }
            .forEach { relation ->
                TypeCapsule(
                    icon = relation.other.icon(),
                    name = relation.other.defaultName,
                    background = coloristic.from(ColorPicker.getPokeColorForType(relation.other.defaultName)),
                    multiplier = relation.magnitude.modifier
                )
            }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TypeSelectionDialog(
    state: TypeDetailsState,
    onTypesSelected: (Pair<PokemonTypeExpected, PokemonTypeExpected?>) -> Unit,
    onDismiss: () -> Unit
) {
    val settings = LocalSettings.current
    val coloristic = LocalColoristic.current

    if (state.showTypeSelection) {
        var primary: PokemonTypeExpected? by remember(state) {
            mutableStateOf(state.primaryType.expected)
        }
        var secondary: PokemonTypeExpected? by remember(state) {
            mutableStateOf(state.secondaryType?.expected)
        }

        PokeDialog(
            onDismissRequest = { onDismiss() },
            content = {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxHeight(0.9f)
                ) {
                    stickyHeader {
                        SubPokeHeader(
                            text = stringResource(id = R.string.type_details_types),
                            secondary = false,
                            modifier = Modifier
                                .background(color = coloristic.background)
                                .padding(16.dp)
                                .fillMaxWidth()
                        )
                    }

                    items(state.allTypes) { type ->
                        val callback = {
                            when {
                                type.expected == primary -> primary = null
                                type.expected == secondary -> secondary = null
                                primary == null -> primary = type.expected
                                secondary == null -> secondary = type.expected
                            }
                            if (primary == null && secondary != null) {
                                primary = secondary
                                secondary = null
                            }
                            if (primary != null) {
                                onTypesSelected(primary!! to secondary)
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { callback() }
                                .padding(8.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Image(
                                    painter = type.expected.icon(),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                ParagraphPokeHeader(
                                    text = type.getLocalized(settings.localization)
                                )
                            }

                            Checkbox(
                                checked = type.expected == primary || type.expected == secondary,
                                onCheckedChange = { callback() }
                            )
                        }
                    }

                    item {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Button(
                                onClick = { onDismiss() },
                            ) {
                                Text(
                                    text = stringResource(id = R.string.type_details_ok),
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}