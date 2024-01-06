package com.example.pokeapp.fake

import com.example.pokeapp.data.AppRepository
import com.example.pokeapp.model.Games.Generation
import com.example.pokeapp.model.Pokemon.Pokemon
import com.example.pokeapp.model.Pokemon.PokemonDetail
import com.example.pokeapp.model.Type.Type
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeApiAppRepository : AppRepository{
    override fun getGenerations(): Flow<List<Generation>> {
        return flowOf(FakeDataSource.generations.toMutableList())
    }

    override suspend fun insertGeneration(generation: Generation) {
        FakeDataSource.generations.toMutableList().add(generation)
    }

    override suspend fun refreshGeneration() {
        FakeDataSource.generations.toMutableList().clear()
    }

    override fun getTypes(): Flow<List<Type>> {
        return flowOf(FakeDataSource.types.toMutableList())
    }

    override suspend fun insertType(type: Type) {
        FakeDataSource.types.toMutableList().add(type)
    }

    override suspend fun refreshType() {
        FakeDataSource.types.toMutableList().clear()
    }

    override fun getPokemon(): Flow<List<Pokemon>> {
        return flowOf(FakeDataSource.pokemons.toMutableList())
    }

    override suspend fun insertPokemon(pokemon: Pokemon) {
        FakeDataSource.pokemons.toMutableList().add(pokemon)
    }

    override suspend fun refreshPokemon() {
        FakeDataSource.pokemons.toMutableList().clear()
    }

    override suspend fun getPokemonDetail(name: String): PokemonDetail? {
        return FakeDataSource.pokemonDetails.find { it.name == name }
    }

    override fun getPokemonByName(name: String): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }
}