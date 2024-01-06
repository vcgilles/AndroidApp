package com.example.pokeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for Pokemon types. This interface provides methods
 * for performing CRUD (Create, Read, Update, Delete) operations related to Pokemon types
 * in the Room database.
 */
@Dao
interface TypeDao {
    /**
     * Inserts a Pokemon type into the database. If a conflict occurs, the existing entry is replaced.
     *
     * @param type The [DbType] object to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: DbType)


    /**
     * Retrieves all Pokemon types from the database.
     *
     * @return A [Flow] emitting a list of [DbType] objects representing all Pokemon types.
     */
    @Query("SELECT * FROM DbType")
    fun getAllTypes(): Flow<List<DbType>>

}