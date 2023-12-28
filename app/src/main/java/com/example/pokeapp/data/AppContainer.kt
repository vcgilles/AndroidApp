package com.example.pokeapp.data

import android.content.Context
import com.example.pokeapp.data.database.AppDatabase
import com.example.pokeapp.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {

    val appRepository : AppRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {
    private val BASE_URL =
        "https://pokeapi.co/api/v2/"

    // Configures Retrofit for networking, using Kotlin serialization for JSON processing.
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }
            .asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    // Lazily initializes the ApiService using Retrofit.
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // Provides a single instance of AppRepository throughout the app lifecycle.
    override val appRepository: AppRepository by lazy {
        CachingAppRepository(
            apiService,
            AppDatabase.getDatabase(context = context).generationDao(),
        )
    }
}