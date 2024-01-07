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
import com.example.pokeapp.network.GamesApi.ApiGameState
import com.example.pokeapp.network.GamesApi.GenerationListState
import com.example.pokeapp.network.TypeApi.ApiTypeState
import com.example.pokeapp.network.TypeApi.TypeListState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * ViewModel responsible for providing data related to the home screen.
 *
 * @property appRepository The repository for accessing data related to the PokeApp application.
 * @property uiGenerationListState The [StateFlow] representing the UI state for the list of generations.
 * @property uiTypeListState The [StateFlow] representing the UI state for the list of Pokemon types.
 * @property apiGameState The current state of the API call for generations (Loading, Success, or Error).
 * @property apiTypeState The current state of the API call for Pokemon types (Loading, Success, or Error).
 */
class HomeViewModel(private val appRepository: AppRepository): ViewModel() {



    lateinit var uiGenerationListState: StateFlow<GenerationListState>

    lateinit var uiTypeListState: StateFlow<TypeListState>

    var apiGameState: ApiGameState by mutableStateOf(ApiGameState.Loading)
        private set

    var apiTypeState : ApiTypeState by mutableStateOf(ApiTypeState.Loading)
        private set

    /**
     * Initializes the ViewModel by fetching data for generations and types.
     */
    init{
        getGenerations()
        getTypes()
    }

    /**
     * Fetches data for generations and updates the UI state accordingly.
     */
    fun getGenerations() {
        try {
            viewModelScope.launch { appRepository.refreshGeneration() }
            uiGenerationListState = appRepository.getGenerations().map { GenerationListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(500_000L),
                    initialValue = GenerationListState(listOf())
                )
            apiGameState = ApiGameState.Success
        } catch (e: SocketTimeoutException) {
            apiGameState = ApiGameState.Error
        } catch (e: IOException) {
            apiGameState = ApiGameState.Error
        }
    }
    /**
     * Fetches data for Pokemon types and updates the UI state accordingly.
     */
    fun getTypes() {
        try {
            viewModelScope.launch { appRepository.refreshType() }
            uiTypeListState = appRepository.getTypes().map { TypeListState(it)}
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(500_000L),
                    initialValue = TypeListState(listOf())
                )
            apiTypeState = ApiTypeState.Success
        } catch (e: SocketTimeoutException) {
            apiTypeState = ApiTypeState.Error
        } catch (e: IOException) {
            apiTypeState = ApiTypeState.Error
        }
    }

    /**
     * A companion object containing a [Factory] property for creating instances of [HomeViewModel].
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppApplication)
                val appRepository = application.container.appRepository
                HomeViewModel(appRepository)
            }
        }
    }


}