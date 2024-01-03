package com.example.pokeapp.model.Games

import kotlin.random.Random

data class Generation(
    val name: String,
    val image: String = "https://picsum.photos/200/300?random=${Random.nextInt(0, 100)}",
    )