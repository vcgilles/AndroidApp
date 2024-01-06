package com.example.pokeapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import com.example.pokeapp.ui.components.Destinations
import com.example.pokeapp.ui.components.MainApplication
import com.example.pokeapp.ui.components.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigationTest {

    private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MainApplication(navController = navController, navigationType = NavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun NavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.Start.name)
    }

    private fun navigateToHomeScreen() {
        composeTestRule.onNodeWithContentDescription("Home")
            .performClick()
    }

    @Test
    fun NavHost_verifyHomeButtonShownOnStartScreen() {
        composeTestRule.onNodeWithContentDescription("Home").assertExists()
    }

    @Test
    fun NavHost_verifyPokemonListButtonShownOnStartScreen() {
        composeTestRule.onNodeWithContentDescription("PokemonList").assertExists()
    }

    @Test
    fun NavHost_clickOnHome_navigatesToHome() {
        navigateToHomeScreen()
        navController.assertCurrentRouteName(Destinations.Start.name)
    }
    @Test
    fun NavHost_clickOnPokemonlist_navigatesToPokemonlistScreen() {
        navigateToHomeScreen()
        composeTestRule.onNodeWithContentDescription("PokemonList")
            .performClick()
        navController.assertCurrentRouteName(Destinations.PokemonList.name)
    }

    @Test
    fun NavHost_clickOnPokemon_navigatesToPokemonDetailScreen() {
        navigateToHomeScreen()
        Thread.sleep(1000)
        composeTestRule.onNodeWithContentDescription("PokemonList")
            .performClick()
        Thread.sleep(5000)
        composeTestRule.onNodeWithContentDescription("PokemonList")
            .performClick()
        Thread.sleep(5000)
        composeTestRule.onNodeWithTag("raticate")
            .performClick()
        //timer
        Thread.sleep(5000)
        composeTestRule.onNodeWithText("raticate")
            .assertIsDisplayed()
    }

}