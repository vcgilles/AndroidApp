package com.example.pokeapp.model.Pokemon



/**
 * Data class representing detailed information about a Pokemon.
 *
 * @property id The unique identifier of the Pokemon.
 * @property name The name of the Pokemon.
 * @property height The height of the Pokemon.
 * @property weight The weight of the Pokemon.
 * @property image The URL of the image associated with the Pokemon.
 */
data class PokemonDetail (
    val id: Int,
    val name: String? = null,
    val height: Int? = null ,
    val weight: Int? = null ,
    val image: String  = image(id),
)

/**
 * Function to generate the image URL for a Pokemon based on its ID.
 *
 * @param id The unique identifier of the Pokemon.
 * @return The URL of the Pokemon's image.
 */
fun image(id: Int) : String{
    val number = reformatInt(id)
    return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/${number}.png"
}

/**
 * Function to reformat an integer to a 3-digit string.
 *
 * @param number The integer to be reformatted.
 * @return The reformatted string.
 */
fun reformatInt(number: Int): String {
    return String.format("%03d", number)
}