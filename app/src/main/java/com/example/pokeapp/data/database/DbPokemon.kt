package com.example.pokeapp.data.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.model.Pokemon.Pokemon


/**
 * This class represents a data entity for a Pokemon in the Room database.
 *
 * @property name The name of the Pokemon.
 * @property url The URL associated with the Pokemon.
 */
@Entity
class DbPokemon (
    @PrimaryKey
    val name: String,
    val url: String,
)

/**
 * Extension function to convert a [Pokemon] object to its corresponding [DbPokemon] representation.
 *
 * @receiver The source [Pokemon] object.
 * @return The converted [DbPokemon] object.
 */
fun Pokemon.asDbPokemon() : DbPokemon = DbPokemon  (
    name = name,
    url = url
)

/**
 * Extension function to convert a [DbPokemon] object to its corresponding [Pokemon] representation.
 *
 * @receiver The source [DbPokemon] object.
 * @return The converted [Pokemon] object.
 */
fun DbPokemon.asDomainPokemon() : Pokemon = Pokemon(
    name = name,
    url = url,
)
/**
 * Extension function to convert a list of [DbPokemon] objects to a list of [Pokemon] objects.
 *
 * @receiver The source list of [DbPokemon] objects.
 * @return The converted list of [Pokemon] objects.
 */
fun List<DbPokemon>.asDomainPokemon() = map { it.asDomainPokemon() }