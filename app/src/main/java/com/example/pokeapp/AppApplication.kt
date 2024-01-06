package com.example.pokeapp

import android.app.Application
import com.example.pokeapp.data.AppContainer
import com.example.pokeapp.data.DefaultAppContainer

/**
 * Custom [Application] class for initializing the PokeApp application.
 *
 * This class is responsible for creating and managing the application-level components and services.
 *
 * @property container The application container responsible for managing dependencies and providing access to application-level services.
 */
class AppApplication : Application() {

    /**
     * The application container responsible for managing dependencies and providing access to application-level services.
     */
    lateinit var container: AppContainer

    /**
     * Called when the application is first created. Initializes the application container.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}