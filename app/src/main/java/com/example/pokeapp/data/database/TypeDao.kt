package com.example.pokeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: DbType)

    @Query("SELECT * FROM DbType")
    fun getAllTypes(): Flow<List<DbType>>

}