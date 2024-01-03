package com.example.pokeapp.model.Type

import kotlin.random.Random

data class Type (
    val type: String,
    val image: String = "https://picsum.photos/200/300?random=${Random.nextInt(0, 100)}",
)