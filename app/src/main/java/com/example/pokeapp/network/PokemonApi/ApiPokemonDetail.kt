package com.example.pokeapp.network.PokemonApi

import com.example.pokeapp.model.Pokemon.PokemonDetail
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing detailed information about a Pokemon from the API.
 *
 * @property id The unique identifier of the Pokemon.
 * @property name The name of the Pokemon.
 * @property height The height of the Pokemon.
 * @property weight The weight of the Pokemon.
 */
@Serializable
data class ApiPokemonDetail (
    val id: Int,
    val name : String? = null,
    val height: Int? = 100,
    val weight: Int? = 100,
)

/**
 * Extension function to convert an [ApiPokemonDetail] to a [PokemonDetail] object.
 *
 * @receiver The source [ApiPokemonDetail].
 * @return A [PokemonDetail] object.
 */
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
