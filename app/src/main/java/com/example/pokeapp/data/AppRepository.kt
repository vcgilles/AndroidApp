package com.example.pokeapp.data

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.database.GenerationDao
import com.example.pokeapp.data.database.PokemonDao
import com.example.pokeapp.data.database.TypeDao
import com.example.pokeapp.data.database.asDbGeneration
import com.example.pokeapp.data.database.asDbPokemon
import com.example.pokeapp.data.database.asDbType
import com.example.pokeapp.data.database.asDomainGenerations
import com.example.pokeapp.data.database.asDomainPokemon
import com.example.pokeapp.data.database.asDomainTypes
import com.example.pokeapp.network.ApiService
import com.example.pokeapp.network.GamesApi.asDomainObjects
import com.example.pokeapp.network.getGenerationAsFlow
import com.example.pokeapp.model.Games.Generation
import com.example.pokeapp.model.Pokemon.Pokemon
import com.example.pokeapp.model.Pokemon.PokemonDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import com.example.pokeapp.model.Type.Type
import com.example.pokeapp.network.PokemonApi.asDomainObject
import com.example.pokeapp.network.PokemonApi.asDomainObjects
import com.example.pokeapp.network.TypeApi.asDomainObjects
import com.example.pokeapp.network.getPokemonAsFlow
import com.example.pokeapp.network.getTypeAsFlow
import kotlinx.coroutines.launch

/**
 * Interface defining the contract for the application repository in the PokeApp.
 * It provides methods for accessing and managing data related to Pokemon generations, types, and individual Pokemon entities.
 */
interface AppRepository {
    /**
     * Retrieves a [Flow] emitting a list of Pokemon generations.
     */
    fun getGenerations(): Flow<List<Generation>>

    /**
     * Inserts a Pokemon generation into the repository.
     */
    suspend fun insertGeneration(generation: Generation)

    /**
     * Refreshes the Pokemon generations data in the repository.
     */
    suspend fun refreshGeneration()

    /**
     * Retrieves a [Flow] emitting a list of Pokemon types.
     */
    fun getTypes(): Flow<List<Type>>

    /**
     * Inserts a Pokemon type into the repository.
     */
    suspend fun insertType(type: Type)

    /**
     * Refreshes the Pokemon types data in the repository.
     */
    suspend fun refreshType()

    /**
     * Retrieves a [Flow] emitting a list of Pokemon entities.
     */
    fun getPokemon(): Flow<List<Pokemon>>

    /**
     * Inserts a Pokemon entity into the repository.
     */
    suspend fun insertPokemon(pokemon: Pokemon)

    /**
     * Refreshes the Pokemon entities data in the repository.
     */
    suspend fun refreshPokemon()

    /**
     * Retrieves detailed information about a specific Pokemon by name.
     *
     * @param name The name of the Pokemon.
     * @return A [PokemonDetail] object representing detailed information about the Pokemon.
     */
    suspend fun getPokemonDetail(name: String): PokemonDetail?

    /**
     * Retrieves a [Flow] emitting a list of Pokemon entities matching the given name.
     *
     * @param name The name of the Pokemon to search for.
     */
    fun getPokemonByName(name: String): Flow<List<Pokemon>>

}
/**
 * Implementation of [AppRepository] that uses local caching for data retrieval and updates.
 *
 * @param apiService The API service for making network requests.
 * @param generationDao Data Access Object (DAO) for Pokemon generations.
 * @param typeDao Data Access Object (DAO) for Pokemon types.
 * @param pokemonDao Data Access Object (DAO) for individual Pokemon entities.
 */
 class CachingAppRepository(
    private val apiService: ApiService,
    private val generationDao: GenerationDao,
    private val typeDao: TypeDao,
    private val pokemonDao: PokemonDao
) : AppRepository {

    override fun getGenerations(): Flow<List<Generation>> {
        return generationDao.getAllGenerations().map {
            it.asDomainGenerations()
        }.onEach {
            if (it.isEmpty()) {
                refreshGeneration()
            }
        }
    }
    override fun getTypes(): Flow<List<Type>> {
        return typeDao.getAllTypes().map {
            it.asDomainTypes()
        }.onEach {
            if (it.isEmpty()) {
                refreshType()
            }
        }
    }
    override fun getPokemon(): Flow<List<Pokemon>> {
        return pokemonDao.getAllPokemons().map {
            it.asDomainPokemon()
        }.onEach {
            if (it.isEmpty()) {
                refreshPokemon()
            }
        }
    }


    override suspend fun insertGeneration(generation: Generation) {
        generationDao.insertGeneration(generation.asDbGeneration())
    }
    override suspend fun insertType(type: Type) {
        typeDao.insertType(type.asDbType())
    }
    override suspend fun insertPokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemon(pokemon.asDbPokemon())

    }

    override suspend fun refreshGeneration() {
        try {
            apiService.getGenerationAsFlow().asDomainObjects().collect {
                for (generation in it) {
                    insertGeneration(generation)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    override suspend fun refreshType() {
        try {
            apiService.getTypeAsFlow().asDomainObjects().collect {
                for (type in it) {
                    insertType(type)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun refreshPokemon() {
        try {
            apiService.getPokemonAsFlow(20,0).asDomainObjects().collect {
                for (pokemon in it) {
                    insertPokemon(pokemon)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

     override suspend fun getPokemonDetail(name: String): PokemonDetail? {
            return try {
                apiService.getPokemonDetail(name).asDomainObject()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
     }

     override fun getPokemonByName(name: String): Flow<List<Pokemon>> {
         return pokemonDao.getPokemonByName(name).map{it.asDomainPokemon()}

     }


 }