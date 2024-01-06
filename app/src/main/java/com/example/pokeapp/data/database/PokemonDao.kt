package com.example.pokeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokeapp.model.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: DbPokemon)

    @Query("SELECT * FROM DbPokemon")
    fun getAllPokemons(): Flow<List<DbPokemon>>

    @Query("SELECT * FROM DbPokemon WHERE name LIKE '%' || :name || '%'")
    fun getPokemonByName(name: String): Flow<List<DbPokemon>>

}