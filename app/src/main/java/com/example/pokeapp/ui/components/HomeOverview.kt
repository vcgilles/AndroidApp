package com.example.templateapplication.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapp.network.GamesApi.ApiGameState
import com.example.pokeapp.viewmodel.HomeViewModel



@Composable
fun  HomeOverview(viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory), )
{

    val homeApiState = viewModel.apiGameState

    when(homeApiState){
        is ApiGameState.Error -> {
            Text("Error")
        }
        is ApiGameState.Loading -> {
            Text("Loading")
        }
        is ApiGameState.Success ->{

        }
    }
}
