package com.skosc.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.core.os.bundleOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import java.io.Serializable

/**
 * @author a.l.yakovlev
 */
private const val BS_ARG_ARGUMENTS = "bs_arguments"

val LocalNavigator: ProvidableCompositionLocal<Navigator> = compositionLocalOf { NavigatorNoop() }

interface Navigator {

    fun navigate(route: String, args: Map<String, Any>)

    fun <T : Serializable> navigate(route: Route<T>, arg: T) {
        navigate(route.route, mapOf(BS_ARG_ARGUMENTS to arg))
    }
}

fun Navigator.navigate(route: Route<NoArg>) {
    navigate(route, NoArg)
}

@Suppress("UNCHECKED_CAST")
fun <T> NavGraphBuilder.register(route: Route<T>) {
    composable(route.route) { bse ->
        InnerPage(bse, route)
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.registerBottomSheet(route: Route<*>) {
    bottomSheet(route.route) { bse ->
        InnerPage(bse, route)
    }
}

@Composable
@Suppress("UNCHECKED_CAST")
private fun <T> InnerPage(
    bse: NavBackStackEntry,
    route: Route<T>,
) {
    val arg = bse.arguments?.getSerializable(BS_ARG_ARGUMENTS) as? T
    route.content(arg ?: (NoArg as T))
}

class NavigatorImpl(private val navController: NavController) : Navigator {

    override fun navigate(route: String, args: Map<String, Any>) {
        navController.currentBackStackEntry?.arguments?.clear()
        navController.navigate(route)
        navController.currentBackStackEntry?.arguments?.putAll(
            bundleOf(*args.map { it.key to it.value }.toTypedArray())
        )
    }
}

class NavigatorNoop : Navigator {

    override fun navigate(route: String, args: Map<String, Any>) = Unit
}
