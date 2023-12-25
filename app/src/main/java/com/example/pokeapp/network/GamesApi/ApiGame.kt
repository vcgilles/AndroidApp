package com.example.pokeapp.network.GamesApi

import kotlinx.serialization.Serializable

@Serializable
data class ApiGameResponse(
    val generations : List<Generation>
)

@Serializable
data class Generation(
    val id: Int,
    val name: String,
)

fun ApiGameResponse.asDomainObjects(): List<Generation> {
    return generations.map {
        Generation(
            id = it.id,
            name = it.name,
        )
    }
}