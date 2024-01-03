package com.example.pokeapp.model.Pokemon

import kotlin.random.Random

data class Pokemon (
    val name: String,
    val url: String ,
    val image: String  = img(url),
    val PokedexNr: Int = extractNumber(url).toInt(),
)

fun img(url: String) : String{
    val number = extractNumber(url)
    return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${number}.png"
}

fun extractNumber(url: String): String {
    val regex = "/(\\d+)/$".toRegex()
    val matchResult = regex.find(url)
    val extractedNumber = matchResult?.groupValues?.get(1)?.toIntOrNull() ?: -1
    return String.format("%03d", extractedNumber)
}
