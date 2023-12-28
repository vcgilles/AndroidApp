package com.example.pokeapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokeapp.ui.theme.PokeAppTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Modifier

import com.example.templateapplication.ui.components.TopBar
import com.example.templateapplication.ui.components.BottomBar

@Composable
fun MainApplication(navController: NavHostController = rememberNavController()) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val isStartDestination = currentBackStackEntry?.destination?.route == Destinations.Start.name

    val goStartScreen: () -> Unit = {
        navController.popBackStack(
            Destinations.Start.name,
            inclusive = false,
        )
    }

    Scaffold(
        topBar = {
            TopBar {
                if (!isStartDestination) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Go back")
                    }
                }
            }
        },
        bottomBar =
        {
            BottomBar(
                currentBackStackEntry?.destination?.route,
                onHome = goStartScreen,
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Start.name,
            Modifier.padding(innerPadding)
        ) {
                composable(Destinations.Start.name) {

                }
          }
        }
    }




@Preview
@Composable
fun ScaffoldExamplePreview() {
    PokeAppTheme {
        MainApplication(navController = rememberNavController())
    }
}