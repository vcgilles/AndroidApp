package com.example.pokeapp.data.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.model.Pokemon.Pokemon


@Entity
class DbPokemon (
    @PrimaryKey
    val name: String,
    val url: String,
)
fun Pokemon.asDbPokemon() : DbPokemon = DbPokemon  (
    name = name,
    url = url
)

fun DbPokemon.asDomainPokemon() : Pokemon = Pokemon(
    name = name,
    url = url,
)

fun List<DbPokemon>.asDomainPokemon() = map { it.asDomainPokemon() }