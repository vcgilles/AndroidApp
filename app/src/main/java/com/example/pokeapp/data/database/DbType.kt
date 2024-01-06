package com.example.pokeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.model.Type.Type

/**
 * This class represents a data entity for a Pokemon type in the Room database.
 *
 * @property type The name of the Pokemon type.
 */
@Entity
class DbType (
    @PrimaryKey
    val type: String,
    )

    /**
     * Extension function to convert a [Type] object to its corresponding [DbType] representation.
     *
     * @receiver The source [Type] object.
     * @return The converted [DbType] object.
     */
    fun Type.asDbType() : DbType = DbType  (
        type = type
    )

    /**
     * Extension function to convert a [DbType] object to its corresponding [Type] representation.
     *
     * @receiver The source [DbType] object.
     * @return The converted [Type] object.
     */
    fun DbType.asDomainType() : Type = Type(
        type = type
    )

/**
 * Extension function to convert a list of [DbType] objects to a list of [Type] objects.
 *
 * @receiver The source list of [DbType] objects.
 * @return The converted list of [Type] objects.
 */
    fun List<DbType>.asDomainTypes() = map { it.asDomainType() }