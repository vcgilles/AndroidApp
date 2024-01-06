package com.example.pokeapp.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource


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