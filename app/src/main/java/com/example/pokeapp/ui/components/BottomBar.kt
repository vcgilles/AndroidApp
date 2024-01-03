package com.example.pokeapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource


@Composable
fun BottomBar(
    currentBackStackEntry: String?,
    onHome: () -> Unit,
    onPokemonList: () -> Unit,
) {
    val context = LocalContext.current

    BottomAppBar(
        containerColor = Color(0xFFDEE8D5),
        contentColor = Color.Black,
        actions = {
            NavigationBarItem(
                selected = currentBackStackEntry == Destinations.Start.name,
                onClick = onHome,
                icon = {
                    Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.testTag("Home"))
                }
            )
            NavigationBarItem(
                selected = currentBackStackEntry == Destinations.PokemonList.name,
                onClick = onPokemonList,
                icon = {
                    Icon(Icons.Default.List, contentDescription = "PokemonList", modifier = Modifier.testTag("PokemonList"))
                }
            )
        }
    )
}
