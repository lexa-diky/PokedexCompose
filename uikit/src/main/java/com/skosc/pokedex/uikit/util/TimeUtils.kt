package com.skosc.pokedex.uikit.util

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kotlinx.datetime.Instant
import java.util.*

@Composable
fun Instant.formatLocal(): String {
    val dateFormat = DateFormat.getDateFormat(LocalContext.current)
    return dateFormat.format(Date(toEpochMilliseconds()))
}