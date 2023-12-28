package com.example.pokeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GenerationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeneration(generation: DbGeneration)
    @Query("SELECT * FROM DbGeneration")
    fun getAllGenerations(): Flow<List<DbGeneration>>
}