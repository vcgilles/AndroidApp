package com.example.pokeapp.model.Pokemon

import kotlin.random.Random

data class PokemonDetail (
    val id: Int,
    val name: String? = null,
    val height: Int? = null ,
    val weight: Int? = null ,
    val image: String  = image(id),
)

fun image(id: Int) : String{
    val number = reformatInt(id)
    return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${number}.png"
}

fun reformatInt(number: Int): String {
    return String.format("%03d", number)
}