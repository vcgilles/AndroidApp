package com.example.pokeapp.network.TypeApi

import com.example.pokeapp.model.Type.Type

/**
 * Sealed interface representing the different states of the API response for Pokemon types.
 */
sealed interface ApiTypeState {

    /**
     * Represents the state when there is an error in the API response.
     */
    object Error : ApiTypeState

    /**
     * Represents the state when the API response is still loading.
     */
    object Loading : ApiTypeState

    /**
     * Represents the state when the API response is successful.
     */
    object Success : ApiTypeState
}

/**
 * Data class representing the state of the list of Pokemon types.
 *
 * @property typeList The list of Pokemon types.
 */
data class TypeListState(
    val typeList: List<Type> = listOf()
)