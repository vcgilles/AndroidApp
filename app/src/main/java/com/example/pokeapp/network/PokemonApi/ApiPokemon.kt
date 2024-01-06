package com.example.pokeapp.network.PokemonApi

import com.example.pokeapp.model.Games.Generation
import com.example.pokeapp.model.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing the response from the API for Pokemon entities.
 *
 * @property results The list of API representations of Pokemon entities.
 */
@Serializable
data class ApiPokemonResponse(
    val results : List<ApiPokemon>
)

/**
 * Serializable data class representing an API representation of a Pokemon entity.
 *
 * @property name The name of the Pokemon entity.
 * @property url The URL associated with the Pokemon entity.
 */
@Serializable
data class ApiPokemon(
    val name: String,
    val url: String,
)

/**
 * Extension function to convert a [Flow] of [ApiPokemonResponse] to a [Flow] of lists of [Pokemon].
 *
 * @receiver The source [Flow] of [ApiPokemonResponse].
 * @return A [Flow] emitting lists of [Pokemon] objects.
 */
fun Flow<ApiPokemonResponse>.asDomainObjects(): Flow<List<Pokemon>> {
    return map {
        it.asDomainObjects()
    }
}

/**
 * Extension function to convert an [ApiPokemonResponse] to a list of [Pokemon] objects.
 *
 * @receiver The source [ApiPokemonResponse].
 * @return A list of [Pokemon] objects.
 */
fun ApiPokemonResponse.asDomainObjects(): List<Pokemon> {
    return results.map {
        Pokemon(
            name = it.name,
            url = it.url
        )
    }
}