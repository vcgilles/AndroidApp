package com.example.pokeapp.ui.PokemonScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokeapp.model.Pokemon.Pokemon
import com.example.pokeapp.ui.components.NavigationType
import com.example.pokeapp.viewmodel.PokemonViewModel

/**
 * Composable function representing the bottom part of a Pokemon card.
 *
 * @param pokemon The [Pokemon] object containing information about the Pokemon.
 * @param onPokemonClick The callback to be invoked when the Pokemon card is clicked.
 */
@Composable
fun PokemonCardBottom(pokemon: Pokemon, onPokemonClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${pokemon.name}",
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            Row {
                AsyncImage(
                    model = pokemon.image,
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { onPokemonClick(pokemon.name) },
                    modifier = Modifier

                ) {
                    Text(
                        text = "Meer info",
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.White
                        ),
                    )
                }
            }
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(color = Color.Gray)
            .padding(vertical = 8.dp)
    )
}

/**
 * Composable function representing the rail part of a Pokemon card.
 *
 * @param pokemon The [Pokemon] object containing information about the Pokemon.
 * @param onPokemonClick The callback to be invoked when the Pokemon card is clicked.
 */
@Composable
fun PokemonCardRail(pokemon: Pokemon, onPokemonClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
            ) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "${pokemon.name}",
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Row() {
                    AsyncImage(
                        model = pokemon.image,
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Button(
                        onClick = { onPokemonClick(pokemon.name) },
                        modifier = Modifier
                            .testTag("${pokemon.name}")

                    ) {
                        Text(
                            text = "Meer info",
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White
                            ),
                        )
                    }
                }
            }
        }
    }
}

/**
 * Composable function representing the screen displaying a list of Pokemon cards.
 *
 * @param pokemons The list of [Pokemon] objects to be displayed.
 * @param onPokemonClick The callback to be invoked when a Pokemon card is clicked.
 * @param navigationType The type of navigation (e.g., [NavigationType.BOTTOM_NAVIGATION] or [NavigationType.NAVIGATION_RAIL]).
 * @param viewModel The [PokemonViewModel] used for managing Pokemon data.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(
    pokemons: List<Pokemon>,
    onPokemonClick: (String) -> Unit,
    navigationType: NavigationType,
    viewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)
) {

    val pokemonByName by viewModel.pokemonByName.collectAsState()
    var name by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column {
        Row {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                query = name,
                onQueryChange = { name = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = { active = it },
                placeholder = { "Search pokemon" },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                trailingIcon = {
                    if (active) {
                        Icon(Icons.Filled.Close, contentDescription = "Close", modifier = Modifier.clickable {
                            name = ""
                            active = false
                        })
                    }
                }
            ) {
                viewModel.fetchPokemonByName(name)
            }
        }

        // Display the characters info
        if (pokemonByName.isNotEmpty()) {
            if (navigationType == NavigationType.BOTTOM_NAVIGATION) {
                LazyColumn {
                    items(pokemonByName) { p ->
                        PokemonCardBottom(pokemon = p, onPokemonClick = onPokemonClick)
                    }
                }
            } else {
                LazyRow {
                    items(pokemonByName) { p ->
                        PokemonCardRail(pokemon = p, onPokemonClick = onPokemonClick)
                    }
                }
            }
        } else {
            if (navigationType == NavigationType.BOTTOM_NAVIGATION) {
                LazyColumn {
                    items(pokemons) { p ->
                        PokemonCardBottom(pokemon = p, onPokemonClick = onPokemonClick)
                    }
                }
            } else {
                LazyRow {
                    items(pokemons) { p ->
                        PokemonCardRail(pokemon = p, onPokemonClick = onPokemonClick)
                    }
                }
            }
        }
    }
}


