package com.example.pokeapp.model.Games

import kotlin.random.Random

/**
 * Data class representing a Pokemon generation.
 *
 * @property name The name of the Pokemon generation.
 * @property image The URL of the image associated with the Pokemon generation.
 */
data class Generation(
    val name: String,
    val image: String = "https://picsum.photos/200/300?random=${Random.nextInt(0, 100)}",
    )