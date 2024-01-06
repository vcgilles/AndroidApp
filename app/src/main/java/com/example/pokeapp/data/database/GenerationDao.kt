package com.example.pokeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for Pokemon generations. This interface provides methods
 * for performing CRUD (Create, Read, Update, Delete) operations related to Pokemon generations
 * in the Room database.
 */
@Dao
interface GenerationDao {

    /**
     * Inserts a Pokemon generation into the database. If a conflict occurs, the existing entry is replaced.
     *
     * @param generation The [DbGeneration] object to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeneration(generation: DbGeneration)

    /**
     * Retrieves all Pokemon generations from the database.
     *
     * @return A [Flow] emitting a list of [DbGeneration] objects representing all Pokemon generations.
     */
    @Query("SELECT * FROM DbGeneration")
    fun getAllGenerations(): Flow<List<DbGeneration>>
}