package com.example.pokeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * This class represents the Room Database for the PokeApp application, providing access to data
 * related to Pokemon generations, types, and individual Pokemon entities.
 *
 * @constructor Creates an instance of the [AppDatabase].
 * @property generationDao DAO (Data Access Object) for Pokemon generations.
 * @property typeDao DAO for Pokemon types.
 * @property pokemonDao DAO for individual Pokemon entities.
 */
@Database(
    entities = [DbGeneration::class , DbType::class, DbPokemon::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun generationDao(): GenerationDao

    abstract fun typeDao(): TypeDao

    abstract fun pokemonDao(): PokemonDao

    /**
     * A companion object that holds the singleton instance of [AppDatabase].
     */

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Gets the singleton instance of [AppDatabase].
         *
         * @param context The application context.
         * @return The [AppDatabase] instance.
         */
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration() // Handle migrations by destroying and rebuilding the database
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}