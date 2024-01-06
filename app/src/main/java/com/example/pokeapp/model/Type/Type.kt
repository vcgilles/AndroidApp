package com.example.pokeapp.model.Type

import kotlin.random.Random

/**
 * Data class representing a Pokemon type.
 *
 * @property type The name of the Pokemon type.
 * @property image The URL of the image associated with the Pokemon type.
 */
data class Type (
    val type: String,
    val image: String = "https://picsum.photos/200/300?random=${Random.nextInt(0, 100)}",
)