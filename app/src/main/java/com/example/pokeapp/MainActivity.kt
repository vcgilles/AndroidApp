package com.example.pokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pokeapp.ui.theme.PokeAppTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.pokeapp.ui.components.MainApplication
import com.example.pokeapp.ui.components.NavigationType

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val windowSize = calculateWindowSizeClass(this)

                    when(windowSize.widthSizeClass){
                        WindowWidthSizeClass.Compact -> {
                            MainApplication(navigationType = NavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            MainApplication(navigationType = NavigationType.NAVIGATION_RAIL)
                        }

                    }
                }
            }
        }
    }
}
