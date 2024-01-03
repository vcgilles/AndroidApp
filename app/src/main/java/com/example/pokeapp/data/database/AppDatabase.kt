package com.example.pokeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [DbGeneration::class , DbType::class, DbPokemon::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun generationDao(): GenerationDao

    abstract fun typeDao(): TypeDao

    abstract fun pokemonDao(): PokemonDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

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