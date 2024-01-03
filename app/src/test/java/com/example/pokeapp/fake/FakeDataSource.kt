package com.example.pokeapp.fake

import com.example.pokeapp.model.Games.Generation
import com.example.pokeapp.model.Pokemon.Pokemon
import com.example.pokeapp.model.Pokemon.PokemonDetail
import com.example.pokeapp.model.Type.Type

object FakeDataSource {

    val generations = listOf(
        Generation(
            name = "Generation I",
        ),
        Generation(
            name = "Generation 2",
        ),
        Generation(
            name = "Generation 3",
        ),
        Generation(
            name = "Generation 4",
        )
    )
    val types = listOf(
        Type(
            type = "Normal",
        ),
        Type(
            type = "Fire",
        ),
        Type(
            type = "Water",
        ),
        Type(
            type = "Grass",
        ),
        Type(
            type = "Electric",
        ),
    )
    val pokemons = listOf(
        Pokemon(
            name = "Bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/",
        ),
        Pokemon(
            name = "Ivysaur",
            url = "https://pokeapi.co/api/v2/pokemon/2/",
        ),
        Pokemon(
            name = "Venusaur",
            url = "https://pokeapi.co/api/v2/pokemon/3/",
        ),
        Pokemon(
            name = "Charmander",
            url = "https://pokeapi.co/api/v2/pokemon/4/",
        ),
    )
    val pokemonDetails = listOf(
        PokemonDetail(
            id = 1,
            name = "Bulbasaur",
            height = 7,
            weight = 69
            ),
        PokemonDetail(
            id = 2,
            name = "Ivysaur",
            height = 10,
            weight = 130
            ),
        PokemonDetail(
            id = 3,
            name = "Venusaur",
            height = 20,
            weight = 1000
            ),
        PokemonDetail(
            id = 4,
            name = "Charmander",
            height = 6,
            weight = 85
            ),
        )
}