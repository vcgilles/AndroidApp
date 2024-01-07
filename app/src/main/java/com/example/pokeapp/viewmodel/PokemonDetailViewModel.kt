package com.example.pokeapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokeapp.AppApplication
import com.example.pokeapp.data.AppRepository
import com.example.pokeapp.network.PokemonApi.ApiPokemonDetailState
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for providing detailed information about a specific Pokemon.
 *
 * @property appRepository The repository for accessing data related to the PokeApp application.
 * @property pokemonDetailApiState The current state of the API call for Pokemon details (Loading, Success, or Error).
 */
class PokemonDetailViewModel(private val appRepository: AppRepository): ViewModel() {

    /**
     * The current state of the API call for Pokemon details (Loading, Success, or Error).
     */
    var pokemonDetailApiState: ApiPokemonDetailState by mutableStateOf(ApiPokemonDetailState.Loading)
        private set

    /**
     * Fetches detailed information about a specific Pokemon by its name and updates the UI state accordingly.
     *
     * @param name The name of the Pokemon for which detailed information is to be fetched.
     */
    fun getPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                val result = appRepository.getPokemonDetail(name)
                pokemonDetailApiState = ApiPokemonDetailState.Success(result)
            } catch (e: Exception) {
                pokemonDetailApiState = ApiPokemonDetailState.Error
            }
        }
    }

    /**
     * A companion object containing a [Factory] property for creating instances of [PokemonDetailViewModel].
     */
    companion object {
        /**
         * A [Factory] property for creating instances of [PokemonDetailViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppApplication)
                val appRepository = application.container.appRepository
                PokemonDetailViewModel(appRepository)
            }
        }
    }
}