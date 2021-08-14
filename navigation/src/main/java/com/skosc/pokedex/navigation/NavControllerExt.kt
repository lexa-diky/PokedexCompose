package com.skosc.pokedex.navigation

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.navigation.NavController

fun NavController.navigate(destination: Destination, args: Map<String, Any> = emptyMap()) {
    assert(args.keys.containsAll(destination.requiredArguments))
    currentBackStackEntry?.arguments?.clear()
    navigate(destination.path)
    currentBackStackEntry?.arguments?.putAll(
        bundleOf(*args.map { it.key to it.value }.toTypedArray())
    )
}