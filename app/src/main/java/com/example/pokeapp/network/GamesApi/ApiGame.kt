package com.example.pokeapp.network.GamesApi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import com.example.pokeapp.model.Games.Generation

@Serializable
data class ApiGameResponse(
    val generations : List<APIGeneration>
)


@Serializable
data class APIGeneration(
    val id: Int,
    val name: String,
)

fun Flow<ApiGameResponse>.asDomainObjects(): Flow<List<Generation>> {
    return map {
        it.asDomainObjects()
    }
}

fun ApiGameResponse.asDomainObjects(): List<Generation> {
    return generations.map {
        Generation(
            id = it.id,
            name = it.name,
        )
    }
}