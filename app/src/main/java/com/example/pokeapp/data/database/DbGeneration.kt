package com.example.pokeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.model.Games.Generation

@Entity
class DbGeneration (
    @PrimaryKey
    val name: String,
    )

fun Generation.asDbGeneration() : DbGeneration = DbGeneration  (
    name = name
)


fun DbGeneration.asDomainGeneration() : Generation = Generation(
    name = name
)

fun List<DbGeneration>.asDomainGenerations() = map { it.asDomainGeneration() }