package com.example.pokeapp.network.PokemonApi

import com.example.pokeapp.model.Pokemon.Pokemon
import com.example.pokeapp.model.Pokemon.PokemonDetail

sealed interface ApiPokemonState {

    object Error: ApiPokemonState
    object Loading: ApiPokemonState
    object Success: ApiPokemonState

}

data class PokemonListState(val pokemonList: List<Pokemon> = listOf())

sealed interface ApiPokemonDetailState {

    object Error: ApiPokemonDetailState
    object Loading: ApiPokemonDetailState
    data class Success(val pokemonDetail: PokemonDetail?): ApiPokemonDetailState

}

