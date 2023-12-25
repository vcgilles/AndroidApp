package com.example.templateapplication.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapp.network.GamesApi.ApiGameState
import com.example.pokeapp.viewmodel.HomeViewModel



@Composable
fun  HomeOverview(viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory))
{

    val homeApiState = viewModel.ApiGameState

    when(homeApiState){
        is ApiGameState.Error -> {
            Text(text = stringResource(R.string.error))
        }
        is ApiGameState.Loading -> {
            Text(text = stringResource(R.string.loading))
        }
        is ApiGameState.Success ->{

        }
    }
}
