package com.skosc.pokedex.navigation

import android.os.Parcelable
import androidx.navigation.NavController

fun <T: Parcelable> NavController.navigate(destination: Destination<T>) {
    navigate(destination.path)
}