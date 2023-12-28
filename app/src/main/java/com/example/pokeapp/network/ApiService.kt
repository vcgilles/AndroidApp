package com.example.pokeapp.network

import com.example.pokeapp.network.GamesApi.ApiGameResponse
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface ApiService {

    @GET("games")
    suspend fun getGenerations(): ApiGameResponse

}

fun ApiService.getGenerationAsFlow() = flow {
    emit(getGenerations())
}