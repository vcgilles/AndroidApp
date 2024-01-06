package com.example.pokeapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokeapp.model.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow


/**
 * Data Access Object (DAO) interface for Pokemon entities. This interface provides methods
 * for performing CRUD (Create, Read, Update, Delete) operations related to individual Pokemon
 * entities in the Room database.
 */
@Dao
interface PokemonDao {

    /**
     * Inserts a Pokemon entity into the database. If a conflict occurs, the existing entry is replaced.
     *
     * @param pokemon The [DbPokemon] object to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: DbPokemon)

    /**
     * Retrieves all Pokemon entities from the database.
     *
     * @return A [Flow] emitting a list of [DbPokemon] objects representing all Pokemon entities.
     */
    @Query("SELECT * FROM DbPokemon")
    fun getAllPokemons(): Flow<List<DbPokemon>>


    /**
     * Retrieves Pokemon entities from the database that match the given name, using a case-insensitive search.
     *
     * @param name The name of the Pokemon to search for.
     * @return A [Flow] emitting a list of [DbPokemon] objects representing Pokemon entities matching the name.
     */
    @Query("SELECT * FROM DbPokemon WHERE name LIKE '%' || :name || '%'")
    fun getPokemonByName(name: String): Flow<List<DbPokemon>>

}