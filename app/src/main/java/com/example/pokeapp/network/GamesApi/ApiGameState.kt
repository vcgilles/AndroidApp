package com.example.pokeapp.network.GamesApi

sealed interface ApiGameState {

    object Error: ApiGameState
    object Loading: ApiGameState
    object Success: ApiGameState

}

data class GenerationListState(val genetationList: List<Generation> = listOf())