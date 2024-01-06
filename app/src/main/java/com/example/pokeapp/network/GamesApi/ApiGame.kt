package com.example.pokeapp.network.GamesApi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import com.example.pokeapp.model.Games.Generation

/**
 * Serializable data class representing the response from the API for Pokemon generations.
 *
 * @property results The list of API representations of Pokemon generations.
 */
@Serializable
data class ApiGameResponse(
    val results : List<APIGeneration>
)

/**
 * Serializable data class representing an API representation of a Pokemon generation.
 *
 * @property name The name of the Pokemon generation.
 */
@Serializable
data class APIGeneration(
    val name: String,
)

/**
 * Extension function to convert a [Flow] of [ApiGameResponse] to a [Flow] of lists of [Generation].
 *
 * @receiver The source [Flow] of [ApiGameResponse].
 * @return A [Flow] emitting lists of [Generation] objects.
 */
fun Flow<ApiGameResponse>.asDomainObjects(): Flow<List<Generation>> {
    return map {
        it.asDomainObjects()
    }
}
/**
 * Extension function to convert an [ApiGameResponse] to a list of [Generation] objects.
 *
 * @receiver The source [ApiGameResponse].
 * @return A list of [Generation] objects.
 */
fun ApiGameResponse.asDomainObjects(): List<Generation> {
    return results.map {
        Generation(
            name = it.name,
        )
    }
}