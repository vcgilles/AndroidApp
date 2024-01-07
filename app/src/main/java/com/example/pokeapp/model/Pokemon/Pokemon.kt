package com.example.pokeapp.model.Pokemon



/**
 * Data class representing a Pokemon entity.
 *
 * @property name The name of the Pokemon.
 * @property url The URL associated with the Pokemon.
 * @property image The URL of the image associated with the Pokemon.
 * @property PokedexNr The Pokedex number of the Pokemon.
 */
data class Pokemon (
    val name: String,
    val url: String ,
    val image: String  = img(url),
    val PokedexNr: Int = extractNumber(url).toInt(),
)

/**
 * Function to generate the image URL for a Pokemon based on its URL.
 *
 * @param url The URL associated with the Pokemon.
 * @return The URL of the Pokemon's image.
 */
fun img(url: String) : String{
    val number = extractNumber(url)
    return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${number}.png"
}

/**
 * Function to extract the Pokedex number from a Pokemon's URL.
 *
 * @param url The URL associated with the Pokemon.
 * @return The extracted Pokedex number as a formatted string.
 */
fun extractNumber(url: String): String {
    val regex = "/(\\d+)/$".toRegex()
    val matchResult = regex.find(url)
    val extractedNumber = matchResult?.groupValues?.get(1)?.toIntOrNull() ?: -1
    return String.format("%03d", extractedNumber)
}
