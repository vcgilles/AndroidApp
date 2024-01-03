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

class PokemonViewModel(private val appRepository: AppRepository): ViewModel() {

    lateinit var uiPokemonListState: StateFlow<PokemonListState>

    private val _pokemonByName = MutableStateFlow<List<Pokemon>>(emptyList())

    var apiPokemonState: ApiPokemonState by mutableStateOf(ApiPokemonState.Loading)
        private set
    var pokemonDetailApiState: ApiPokemonDetailState by mutableStateOf(ApiPokemonDetailState.Loading)
        private set

    init{
        getPokemon()
    }

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

    fun getPokemonDetail(name: String) {
        Log.i("result detail news", "result: ${name}")
        viewModelScope.launch {
            try {
                val result = appRepository.getPokemonDetail(name)
                pokemonDetailApiState = ApiPokemonDetailState.Success(result)

                Log.i("result detail news", "result: ${result}")


            } catch (e: Exception) {
                pokemonDetailApiState = ApiPokemonDetailState.Error
                Log.i("result detail doctor", "result: ${e.message}")
            }
        }
    }


    val pokemonByName: StateFlow<List<Pokemon>> = _pokemonByName.asStateFlow()

    fun fetchPokemonByName(name: String) {
        viewModelScope.launch {
            try {
                _pokemonByName.value = appRepository.getPokemonByName(name).first()
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Error fetching character: ${e.message}")
                _pokemonByName.value = emptyList()
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppApplication)
                val appRepository = application.container.appRepository
                PokemonViewModel(appRepository)
            }
        }
    }

}