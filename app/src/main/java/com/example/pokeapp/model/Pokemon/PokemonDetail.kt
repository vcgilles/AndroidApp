package com.example.pokeapp.model.Pokemon

import kotlin.random.Random

data class PokemonDetail (
    val id: Int = Random.nextInt(0, 1000),
    val name: String,
    val height: Int = Random.nextInt(0, 1000),
    val weight: Int = Random.nextInt(0, 1000),
    val image: String  = image(id),
)

fun image(id: Int) : String{
    val number = reformatInt(id)
    return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${number}.png"
}

fun reformatInt(number: Int): String {
    return String.format("%03d", number)
}