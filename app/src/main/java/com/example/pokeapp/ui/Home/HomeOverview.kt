package com.example.pokeapp.ui.Home


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapp.network.GamesApi.ApiGameState
import com.example.pokeapp.viewmodel.HomeViewModel
import com.example.pokeapp.ui.components.NavigationType

/**
 * Composable function representing the home overview screen in the PokeApp application.
 *
 * @param viewModel The [HomeViewModel] used to manage data for the home overview screen.
 * @param navigationType The type of navigation (e.g., [NavigationType.BOTTOM_NAVIGATION] or [NavigationType.NAVIGATION_RAIL]).
 */
@Composable
fun  HomeOverview(viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory ), navigationType: NavigationType )
{
    val uiGameList by viewModel.uiGenerationListState.collectAsState()
    val homeApiState = viewModel.apiGameState

    val uiTypeList by viewModel.uiTypeListState.collectAsState()
    val typeApiState = viewModel.apiTypeState

    when(homeApiState){
        is ApiGameState.Error -> {
            Text("Error")
        }
        is ApiGameState.Loading -> {
            Text("Loading")
        }
        is ApiGameState.Success ->{
            screen(generations = uiGameList.genetationList , types = uiTypeList.typeList , navigationType = navigationType)
        }
    }
}
