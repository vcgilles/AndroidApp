package com.example.pokeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.model.Games.Generation

/**
 * This class represents a data entity for a Pokemon generation in the Room database.
 *
 * @property name The name of the Pokemon generation.
 */
@Entity
class DbGeneration (
    @PrimaryKey
    val name: String,
    )

/**
 * Extension function to convert a [Generation] object to its corresponding [DbGeneration] representation.
 *
 * @receiver The source [Generation] object.
 * @return The converted [DbGeneration] object.
 */
fun Generation.asDbGeneration() : DbGeneration = DbGeneration  (
    name = name
)

/**
 * Extension function to convert a [DbGeneration] object to its corresponding [Generation] representation.
 *
 * @receiver The source [DbGeneration] object.
 * @return The converted [Generation] object.
 */
fun DbGeneration.asDomainGeneration() : Generation = Generation(
    name = name
)

/**
 * Extension function to convert a list of [DbGeneration] objects to a list of [Generation] objects.
 *
 * @receiver The source list of [DbGeneration] objects.
 * @return The converted list of [Generation] objects.
 */
fun List<DbGeneration>.asDomainGenerations() = map { it.asDomainGeneration() }