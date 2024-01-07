package com.example.pokeapp.ui.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.material3.NavigationRail
import androidx.compose.material.icons.filled.List


/**
 * Composable function representing navigation using a navigation rail in the PokeApp application.
 *
 * @param currentBackStackEntry The current back stack entry representing the active destination.
 * @param onHome Callback function invoked when the "Home" icon in the navigation rail is clicked.
 * @param onPokemonList Callback function invoked when the "Pokemon List" icon in the navigation rail is clicked.
 */
@Composable
fun RailNavigation(
    currentBackStackEntry: String?,
    onHome: () -> Unit,
    onPokemonList: () -> Unit,)
{
    NavigationRail {
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.Start.name,
            onClick = onHome,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "HomeRail"
                )
            }
        )
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.PokemonList.name,
            onClick = onPokemonList,
            icon = {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "PokemonListRail"
                )
            }
        )
    }
}