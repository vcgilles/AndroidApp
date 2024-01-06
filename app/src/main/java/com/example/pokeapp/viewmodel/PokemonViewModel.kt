package com.example.pokeapp.viewmodel

import android.util.Log
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
import com.example.pokeapp.model.Pokemon.Pokemon
import com.example.pokeapp.network.PokemonApi.ApiPokemonDetailState
import com.example.pokeapp.network.PokemonApi.ApiPokemonState
import com.example.pokeapp.network.PokemonApi.PokemonListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException


/**
 * ViewModel responsible for providing information about Pokemon and handling Pokemon-related UI states.
 *
 * @property appRepository The repository for accessing data related to the PokeApp application.
 * @property uiPokemonListState The UI state for the list of Pokemon.
 * @property _PokemonByName Internal mutable state flow for holding the Pokemon list by name.
 * @property pokemonByName The state flow representing the Pokemon list by name.
 * @property apiPokemonState The current state of the API call for Pokemon data (Loading, Success, or Error).
 */
class PokemonViewModel(private val appRepository: AppRepository): ViewModel() {

    /**
     * The UI state for the list of Pokemon.
     */
    lateinit var uiPokemonListState: StateFlow<PokemonListState>

    /**
     * Internal mutable state flow for holding the Pokemon list by name.
     */
    private val _PokemonByName = MutableStateFlow<List<Pokemon>>(emptyList())

    /**
     * The state flow representing the Pokemon list by name.
     */
    val pokemonByName: StateFlow<List<Pokemon>> = _PokemonByName.asStateFlow()

    /**
     * The current state of the API call for Pokemon data (Loading, Success, or Error).
     */
    var apiPokemonState: ApiPokemonState by mutableStateOf(ApiPokemonState.Loading)
        private set

    /**
     * Initializes the PokemonViewModel by fetching the initial Pokemon data.
     */
    init {
        getPokemon()
    }

    /**
     * Fetches the list of Pokemon and updates the UI state accordingly.
     */
    fun getPokemon() {
        try {
            viewModelScope.launch { appRepository.refreshPokemon() }
            uiPokemonListState = appRepository.getPokemon().map { PokemonListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(500_000L),
                    initialValue = PokemonListState(listOf())
                )
            apiPokemonState = ApiPokemonState.Success
        } catch (e: SocketTimeoutException) {
            apiPokemonState = ApiPokemonState.Error
        } catch (e: IOException) {
            apiPokemonState = ApiPokemonState.Error
        }
    }

    /**
     * Fetches the Pokemon by name and updates the internal state flow.
     *
     * @param name The name of the Pokemon to fetch.
     */
    fun fetchPokemonByName(name: String) {
        viewModelScope.launch {
            try {
                _PokemonByName.value = appRepository.getPokemonByName(name).first()
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Error fetching character: ${e.message}")
                _PokemonByName.value = emptyList()
            }
        }
    }

    /**
     * A companion object containing a [Factory] property for creating instances of [PokemonViewModel].
     */
    companion object {
        /**
         * A [Factory] property for creating instances of [PokemonViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppApplication)
                val appRepository = application.container.appRepository
                PokemonViewModel(appRepository)
            }
        }
    }
}