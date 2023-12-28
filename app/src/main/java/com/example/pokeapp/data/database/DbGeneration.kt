package com.example.pokeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.model.Games.Generation

@Entity
class DbGeneration (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    )

fun Generation.asDbGeneration() : DbGeneration = DbGeneration  (
    id = id,
    name = name,
)


fun DbGeneration.asDomainGeneration() : Generation = Generation(
    id = id,
    name = name,

)

fun List<DbGeneration>.asDomainGenerations() = map { it.asDomainGeneration() }