package com.example.pokeapp.network.TypeApi

import com.example.pokeapp.model.Type.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class ApiTypeResponse (
    val results : List<APIType>
)

@Serializable
data class APIType (
    val name: String,
)
fun Flow<ApiTypeResponse>.asDomainObjects(): Flow<List<Type>> {
    return map {
        it.asDomainObjects()
    }
}

fun ApiTypeResponse.asDomainObjects(): List<Type> {
    return results.map {
        Type(
            type = it.name
        )
    }
}