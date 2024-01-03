package com.example.pokeapp.ui;


import com.example.pokeapp.fake.FakeApiAppRepository
import com.example.pokeapp.network.GamesApi.ApiGameState
import com.example.pokeapp.network.TypeApi.ApiTypeState
import com.example.pokeapp.viewmodel.HomeViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

 class HomeModelTest {
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = HomeViewModel(FakeApiAppRepository())
    }

    @Test
    fun getTypes_methodCall_StateIsSuccessAfterCall() {
        // Arrange
        val expectedApiState = ApiTypeState.Success

        // Act
        viewModel.getTypes()

        // Assert
        Assert.assertEquals(expectedApiState, viewModel.apiTypeState)
    }

     @Test
     fun getGenerations_methodCall_StateIsSuccessAfterCall() {
         // Arrange
         val expectedApiState = ApiGameState.Success

         // Act
         viewModel.getGenerations()

         // Assert
         Assert.assertEquals(expectedApiState, viewModel.apiGameState)
     }

}