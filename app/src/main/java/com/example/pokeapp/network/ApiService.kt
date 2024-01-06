package com.example.pokeapp.network

import com.example.pokeapp.network.GamesApi.ApiGameResponse
import com.example.pokeapp.network.PokemonApi.ApiPokemonDetail
import com.example.pokeapp.network.PokemonApi.ApiPokemonResponse
import com.example.pokeapp.network.TypeApi.ApiTypeResponse
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface defining API endpoints for the PokeApp application.
 */
interface ApiService {

    /**
     * Retrieves the list of Pokemon generations from the API.
     *
     * @return [ApiGameResponse] containing the list of Pokemon generations.
     */
    @GET("generation")
    suspend fun getGenerations(): ApiGameResponse

    /**
     * Retrieves the list of Pokemon types from the API.
     *
     * @return [ApiTypeResponse] containing the list of Pokemon types.
     */
    @GET("type")
    suspend fun getTypes(): ApiTypeResponse

    /**
     * Retrieves the list of Pokemon entities from the API.
     *
     * @param limit The maximum number of entities to retrieve.
     * @param offset The offset for paginating the results.
     * @return [ApiPokemonResponse] containing the list of Pokemon entities.
     */
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): ApiPokemonResponse

    /**
     * Retrieves detailed information about a specific Pokemon by name.
     *
     * @param name The name of the Pokemon.
     * @return [ApiPokemonDetail] containing detailed information about the Pokemon.
     */
    @GET("pokemon/{name}/")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): ApiPokemonDetail
}

/**
 * Extension function to convert the result of [getGenerations] to a [Flow].
 *
 * @receiver The source [ApiService].
 * @return A [Flow] emitting [ApiGameResponse] containing the list of Pokemon generations.
 */
fun ApiService.getGenerationAsFlow() = flow {
    emit(getGenerations())
}

/**
 * Extension function to convert the result of [getTypes] to a [Flow].
 *
 * @receiver The source [ApiService].
 * @return A [Flow] emitting [ApiTypeResponse] containing the list of Pokemon types.
 */
fun ApiService.getTypeAsFlow() = flow {
    emit(getTypes())
}

/**
 * Extension function to convert the result of [getPokemons] to a [Flow].
 *
 * @receiver The source [ApiService].
 * @param limit The maximum number of entities to retrieve.
 * @param offset The offset for paginating the results.
 * @return A [Flow] emitting [ApiPokemonResponse] containing the list of Pokemon entities.
 */
fun ApiService.getPokemonAsFlow(limit: Int?, offset: Int?) = flow {
    emit(getPokemons(limit, offset))
}