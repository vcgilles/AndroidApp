package com.example.pokeapp.network.PokemonApi

import com.example.pokeapp.model.Games.Generation
import com.example.pokeapp.model.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable


@Serializable
data class ApiPokemonResponse(
    val results : List<ApiPokemon>
)


@Serializable
data class ApiPokemon(
    val name: String,
    val url: String,
)

fun Flow<ApiPokemonResponse>.asDomainObjects(): Flow<List<Pokemon>> {
    return map {
        it.asDomainObjects()
    }
}

fun ApiPokemonResponse.asDomainObjects(): List<Pokemon> {
    return results.map {
        Pokemon(
            name = it.name,
            url = it.url
        )
    }
}