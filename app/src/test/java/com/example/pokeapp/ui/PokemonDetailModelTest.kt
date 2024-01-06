package com.example.pokeapp.ui

import com.example.pokeapp.fake.FakeApiAppRepository
import com.example.pokeapp.viewmodel.PokemonDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonDetailModelTest {
    private lateinit var viewModel: PokemonDetailViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = PokemonDetailViewModel(FakeApiAppRepository())
    }

    @Test
    fun getPokemonDetail_methodCall_StateIsSuccessAfterCall() {
        viewModel.getPokemonDetail("bulbasaur")
    }
}