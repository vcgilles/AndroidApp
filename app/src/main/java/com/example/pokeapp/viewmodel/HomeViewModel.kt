package com.example.pokeapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.network.GamesApi.ApiGameState
import com.example.pokeapp.network.GamesApi.GenerationListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class HomeViewModel(): ViewModel() {

    lateinit var uiGenerationListState: StateFlow<GenerationListState>

    var apiGameState: ApiGameState by mutableStateOf(ApiGameState.Loading)
        private set

    init{
        getConditions()
    }

    fun getConditions() {
        try {
            viewModelScope.launch { appRepository.refreshConditions() }
            uiConditionListState = appRepository.getConditions().map { ConditionListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(500_000L),
                    initialValue = ConditionListState(listOf())
                )
            conditionApiState = ConditionApiState.Success
        } catch (e: SocketTimeoutException) {
            conditionApiState = ConditionApiState.Error
        } catch (e: IOException) {
            conditionApiState = ConditionApiState.Error
        }
    }

}