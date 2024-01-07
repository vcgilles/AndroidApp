package com.example.pokeapp.network.TypeApi

import com.example.pokeapp.model.Type.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable


/**
 * Serializable data class representing the response from the API for Pokemon types.
 *
 * @property results The list of API representations of Pokemon types.
 */
@Serializable
data class ApiTypeResponse(
    val results: List<APIType>
)

/**
 * Serializable data class representing an API representation of a Pokemon type.
 *
 * @property name The name of the Pokemon type.
 */
@Serializable
data class APIType(
    val name: String
)

/**
 * Extension function to convert a [Flow] of [ApiTypeResponse] to a [Flow] of lists of [Type].
 *
 * @receiver The source [Flow] of [ApiTypeResponse].
 * @return A [Flow] emitting lists of [Type] objects.
 */
fun Flow<ApiTypeResponse>.asDomainObjects(): Flow<List<Type>> {
    return map {
        it.asDomainObjects()
    }
}

/**
 * Extension function to convert an [ApiTypeResponse] to a list of [Type] objects.
 *
 * @receiver The source [ApiTypeResponse].
 * @return A list of [Type] objects.
 */
fun ApiTypeResponse.asDomainObjects(): List<Type> {
    return results.map {
        Type(
            type = it.name
        )
    }
}