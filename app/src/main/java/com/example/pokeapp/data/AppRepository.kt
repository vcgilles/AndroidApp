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


interface AppRepository {
    fun getGenerations(): Flow<List<Generation>>

    suspend fun insertGeneration(generation: Generation)

    suspend fun refreshGeneration()

    fun getTypes(): Flow<List<Type>>

    suspend fun insertType(type: Type)

    suspend fun refreshType()

    fun getPokemon(): Flow<List<Pokemon>>

    suspend fun insertPokemon(pokemon: Pokemon)

    suspend fun refreshPokemon()

    suspend fun getPokemonDetail(name: String): PokemonDetail?

    fun getPokemonByName(name: String): Flow<List<Pokemon>>

}
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
            apiService.getPokemonAsFlow(151,0).asDomainObjects().collect {
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
         return pokemonDao.getPokemonByName(name).map {
             it.asDomainPokemon()
         }
     }


 }