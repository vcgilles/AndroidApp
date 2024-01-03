package com.example.pokeapp.network.GamesApi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import com.example.pokeapp.model.Games.Generation

@Serializable
data class ApiGameResponse(
    val results : List<APIGeneration>
)


@Serializable
data class APIGeneration(
    val name: String,
)

fun Flow<ApiGameResponse>.asDomainObjects(): Flow<List<Generation>> {
    return map {
        it.asDomainObjects()
    }
}

fun ApiGameResponse.asDomainObjects(): List<Generation> {
    return results.map {
        Generation(
            name = it.name,
        )
    }
}