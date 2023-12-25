package com.example.pokeapp.network

import com.example.pokeapp.network.GamesApi.ApiGameResponse
import retrofit2.http.GET

interface ApiService {

    @GET("games")
    suspend fun getGames(): ApiGameResponse

}