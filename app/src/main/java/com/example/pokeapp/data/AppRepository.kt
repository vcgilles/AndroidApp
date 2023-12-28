package com.example.pokeapp.data

import com.example.pokeapp.data.database.GenerationDao
import com.example.pokeapp.data.database.asDbGeneration
import com.example.pokeapp.data.database.asDomainGenerations
import com.example.pokeapp.network.ApiService
import com.example.pokeapp.network.GamesApi.asDomainObjects
import com.example.pokeapp.network.getGenerationAsFlow
import com.example.pokeapp.model.Games.Generation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach


interface AppRepository {
    fun getGenerations(): Flow<List<Generation>>

    suspend fun insertGeneration(generation: Generation)

    suspend fun refreshGeneration()
}

class CachingAppRepository(
    private val apiService: ApiService,
    private val generationDao: GenerationDao
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

    override suspend fun insertGeneration(generation: Generation) {
        generationDao.insertGeneration(generation.asDbGeneration())
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
}