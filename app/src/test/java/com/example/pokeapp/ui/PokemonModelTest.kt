package com.example.pokeapp.ui

import com.example.pokeapp.fake.FakeApiAppRepository
import com.example.pokeapp.network.PokemonApi.ApiPokemonState
import com.example.pokeapp.viewmodel.PokemonViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonModelTest {
    private lateinit var viewModel: PokemonViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = PokemonViewModel(FakeApiAppRepository())
    }

    @Test
    fun getPokemons_methodCall_StateIsSuccessAfterCall() {
        // Arrange
        val expectedApiState = ApiPokemonState.Success

        // Act
        viewModel.getPokemon()

        // Assert
        Assert.assertEquals(expectedApiState, viewModel.apiPokemonState)
    }

    /*@Test
    fun getPokemons_methodCall_StateIsErrorAfterCall() {
        // Arrange
        val expectedApiState = ApiPokemonState.Error

        // Act
        viewModel.getPokemon()

        // Assert
        Assert.assertEquals(expectedApiState, viewModel.apiPokemonState)
    }*/



}