package com.example.pokeapp.ui.components

import android.util.Log
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
import com.example.pokeapp.ui.Home.HomeOverview
import com.example.pokeapp.ui.PokemonScreen.PokemonDetailScreen
import com.example.pokeapp.ui.PokemonScreen.PokemonScreenOverview
import com.example.pokeapp.ui.components.TopBar
import com.example.pokeapp.ui.components.BottomBar


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
    val goPokemonListScreen: () -> Unit = {
        navController.navigate(Destinations.PokemonList.name)
    }
    val goPokemonDetailScreen: (String) -> Unit = { name ->
        navController.navigate("${Destinations.PokemonDetail.name}/$name")
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
                onPokemonList = goPokemonListScreen,

            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Start.name,
            Modifier.padding(innerPadding)
        ) {
                composable(Destinations.Start.name) {
                    HomeOverview()
                }
                composable(Destinations.PokemonList.name) {
                    PokemonScreenOverview(goPokemonDetailScreen = goPokemonDetailScreen)
                }
                composable(route = "${Destinations.PokemonDetail.name}/{name}") {
                    val name = it.arguments?.getString("name")
                    Log.i("MainAPplicatione", name.toString())
                    name?.let { it1 -> PokemonDetailScreen(pokemonName = it1) }
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