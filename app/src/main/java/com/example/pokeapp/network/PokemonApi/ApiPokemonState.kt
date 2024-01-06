package com.example.pokeapp.network.PokemonApi

import com.example.pokeapp.model.Pokemon.Pokemon
import com.example.pokeapp.model.Pokemon.PokemonDetail

/**
 * Sealed interface representing the different states of the API response for Pokemon entities.
 */
sealed interface ApiPokemonState {

    /**
     * Represents the state when there is an error in the API response.
     */
    object Error : ApiPokemonState

    /**
     * Represents the state when the API response is still loading.
     */
    object Loading : ApiPokemonState

    /**
     * Represents the state when the API response is successful.
     */
    object Success : ApiPokemonState

}

/**
 * Data class representing the state of the list of Pokemon entities.
 *
 * @property pokemonList The list of Pokemon entities.
 */
data class PokemonListState(val pokemonList: List<Pokemon> = listOf())

/**
 * Sealed interface representing the different states of the API response for Pokemon details.
 */
sealed interface ApiPokemonDetailState {

    /**
     * Represents the state when there is an error in the API response.
     */
    object Error : ApiPokemonDetailState

    /**
     * Represents the state when the API response is still loading.
     */
    object Loading : ApiPokemonDetailState

    /**
     * Represents the state when the API response for Pokemon details is successful.
     *
     * @property pokemonDetail The detailed information about the Pokemon.
     */
    data class Success(val pokemonDetail: PokemonDetail?): ApiPokemonDetailState

}

