package com.example.pokeapp.ui.PokemonScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapp.viewmodel.PokemonViewModel
import com.example.pokeapp.network.PokemonApi.ApiPokemonState

@Composable
fun  PokemonScreenOverview (viewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory), goPokemonDetailScreen: (String) -> Unit ) {

    val uiPokemonList by viewModel.uiPokemonListState.collectAsState()
    val pokemonApiState = viewModel.apiPokemonState
    val pokemonByName by viewModel.pokemonByName.collectAsState()

    when(pokemonApiState){
        is ApiPokemonState.Error -> {
            Text("Error")
        }
        is ApiPokemonState.Loading -> {
            Text("Loading")
        }
        is ApiPokemonState.Success ->{

            PokemonScreen(pokemons = uiPokemonList.pokemonList , onPokemonClick = goPokemonDetailScreen)
        }
        else -> {
            Text("Error")
        }
    }

}