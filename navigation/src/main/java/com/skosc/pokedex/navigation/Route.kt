package com.skosc.pokedex.navigation

import androidx.compose.runtime.Composable
import java.io.Serializable

/**
 * @author a.l.yakovlev
 */
object NoArg : Serializable

data class Route<Argument>(
    val route: String,
    internal val content: @Composable (Argument) -> Unit
)

@Suppress("UNCHECKED_CAST")
fun <Argument : Serializable> RouteComposable(route: String, content: @Composable (Argument) -> Unit): Route<Argument> {
    return Route(
        route = route,
        content = { arguments -> content(arguments) }
    )
}

@Suppress("UNCHECKED_CAST")
fun RouteComposable(route: String, content: @Composable () -> Unit): Route<NoArg> {
    return RouteComposable<NoArg>(route) { content() }
}
