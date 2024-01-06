package com.example.pokeapp.network.PokemonApi

import com.example.pokeapp.model.Pokemon.PokemonDetail
import kotlinx.serialization.Serializable

@Serializable
data class ApiPokemonDetail (
    val id: Int,
    val name : String? = null,
    val height: Int? = 100,
    val weight: Int? = 100,
)

fun ApiPokemonDetail.asDomainObject(): PokemonDetail {
    return let {
        PokemonDetail(
            id = it?.id ?: 0,
            name = it?.name,
            height = it?.height,
            weight = it?.weight,
        )
    }
}
