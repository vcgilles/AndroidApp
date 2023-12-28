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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException


class HomeViewModel(private val appRepository: AppRepository): ViewModel() {



    lateinit var uiGenerationListState: StateFlow<GenerationListState>

    var apiGameState: ApiGameState by mutableStateOf(ApiGameState.Loading)
        private set

    init{
        getGenerations()
    }

    private fun getGenerations() {
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