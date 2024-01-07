package com.example.pokeapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


/**
 * Composable function representing the top app bar in the PokeApp application.
 *
 * @param navigationIcon The content of the navigation icon in the top app bar.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    navigationIcon: @Composable () -> Unit,

) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFFDEE8D5),
            titleContentColor = MaterialTheme.colorScheme.primary,

            ),
        title = {
            "Pokemon Application"
        },
        navigationIcon = navigationIcon,

    )
}
