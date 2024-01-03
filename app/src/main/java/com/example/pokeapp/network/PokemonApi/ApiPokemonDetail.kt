package com.example.pokeapp.network.PokemonApi

import com.example.pokeapp.model.Pokemon.PokemonDetail
import kotlinx.serialization.Serializable

@Serializable
data class ApiPokemonDetail (
    val detail : Jonny
)

@Serializable
data class Jonny(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
)

fun ApiPokemonDetail.asDomainObject(): PokemonDetail {
    return this.detail.let {
        PokemonDetail(
            name = it.name
        )
    }
}
