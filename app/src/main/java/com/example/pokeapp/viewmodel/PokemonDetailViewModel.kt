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
import com.example.pokeapp.network.PokemonApi.ApiPokemonDetailState
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val appRepository: AppRepository): ViewModel() {

    var pokemonDetailApiState: ApiPokemonDetailState by mutableStateOf(ApiPokemonDetailState.Loading)
        private set

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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppApplication)
                val appRepository = application.container.appRepository
                PokemonDetailViewModel(appRepository)
            }
        }
    }
}