package com.example.pokeapp.network

import com.example.pokeapp.network.GamesApi.ApiGameResponse
import com.example.pokeapp.network.PokemonApi.ApiPokemonDetail
import com.example.pokeapp.network.PokemonApi.ApiPokemonResponse
import com.example.pokeapp.network.TypeApi.ApiTypeResponse
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("generation")
    suspend fun getGenerations(): ApiGameResponse

    @GET("type")
    suspend fun getTypes(): ApiTypeResponse

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): ApiPokemonResponse

    @GET("pokemon/{name}/")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): ApiPokemonDetail

}

fun ApiService.getGenerationAsFlow() = flow {
    emit(getGenerations())
}

fun ApiService.getTypeAsFlow() = flow {
    emit(getTypes())
}

fun ApiService.getPokemonAsFlow(limit: Int?, offset: Int?) = flow {
    emit(getPokemons(limit, offset))
}