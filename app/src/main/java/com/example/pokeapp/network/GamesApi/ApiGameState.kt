package com.example.pokeapp.network.GamesApi

import com.example.pokeapp.model.Games.Generation


/**
 * Sealed interface representing the different states of the API response for Pokemon generations.
 */
sealed interface ApiGameState {

    /**
     * Represents the state when there is an error in the API response.
     */
    object Error : ApiGameState

    /**
     * Represents the state when the API response is still loading.
     */
    object Loading : ApiGameState

    /**
     * Represents the state when the API response is successful.
     */
    object Success : ApiGameState

}
/**
 * Data class representing the state of the list of Pokemon generations.
 *
 * @property generationList The list of Pokemon generations.
 */
data class GenerationListState(val genetationList: List<Generation> = listOf())