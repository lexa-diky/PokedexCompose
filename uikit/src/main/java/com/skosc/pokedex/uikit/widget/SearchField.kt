package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.theme.LocalColoristic

@Composable
fun SearchField(onQueryUpdated: (String) -> Unit, modifier: Modifier = Modifier) {
    val coloristic = LocalColoristic.current
    val query = remember { mutableStateOf("") }

    TextField(
        value = query.value,
        onValueChange = { newQuery ->
            query.value = newQuery
            onQueryUpdated(newQuery)
        },
        shape = RoundedCornerShape(16.dp),
        placeholder = {
            PokeText(stringResource(id = R.string.uikit_search_query), secondary = false)
        },
        leadingIcon = {
            Image(
                Icons.Default.Search,
                colorFilter = ColorFilter.tint(coloristic.textPrimaryAccent),
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            backgroundColor = LocalColoristic.current.backgroundShadow
        ),
        modifier = modifier
    )
}
