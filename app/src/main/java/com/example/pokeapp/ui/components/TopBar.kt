package com.example.pokeapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.stringResource



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
