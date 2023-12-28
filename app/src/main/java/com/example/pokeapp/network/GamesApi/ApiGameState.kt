package com.example.pokeapp.network.GamesApi

import com.example.pokeapp.model.Games.Generation

sealed interface ApiGameState {

    object Error: ApiGameState
    object Loading: ApiGameState
    object Success: ApiGameState

}

data class GenerationListState(val genetationList: List<Generation> = listOf())