package com.example.pokeapp.ui.PokemonScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokeapp.model.Pokemon.PokemonDetail
import com.example.pokeapp.network.PokemonApi.ApiPokemonDetailState
import com.example.pokeapp.ui.components.NavigationType
import com.example.pokeapp.viewmodel.PokemonDetailViewModel

/**
 * Composable function representing the bottom part of the Pokemon detail screen.
 *
 * @param pokemon The [PokemonDetail] object containing information about the Pokemon.
 */
@Composable
fun PokemonDetailScreenBottom(pokemon : PokemonDetail) {
    Box(modifier = Modifier.fillMaxSize()){
    Card(
        modifier = Modifier
            .padding(6.dp)
            .align(Alignment.Center),
        shape = RoundedCornerShape(15.dp),

    ) {
        Row {
            Text(text = "Nr: ${pokemon.id}", modifier = Modifier.padding(8.dp))
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = pokemon.image,
                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
            )
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${pokemon.name}",
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Height: ${pokemon.height} decimeters",
                    style = TextStyle(
                        fontSize = 25.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Weight: ${pokemon.weight} hectogram",
                    style = TextStyle(
                        fontSize = 25.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(16.dp)
                )

            }
        }
    }

    }
}
/**
 * Composable function representing the rail part of the Pokemon detail screen.
 *
 * @param pokemon The [PokemonDetail] object containing information about the Pokemon.
 */
@Composable
fun PokemonDetailScreenRail(pokemon : PokemonDetail) {
    Box(modifier = Modifier.fillMaxSize()){
        Card(
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(15.dp),

            ) {
            Row {
                Column {
                    AsyncImage(
                        model = pokemon.image,
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${pokemon.name}",
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "PokedexNr: ${pokemon.id}",
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "Height: ${pokemon.height} decimeters",
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "Weight: ${pokemon.weight} hectogram",
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(16.dp)
                    )

                }
            }
        }
    }
}

/**
 * Composable function representing the Pokemon detail screen.
 *
 * @param pokemonName The name of the Pokemon to retrieve details for.
 * @param viewmodel The [PokemonDetailViewModel] used to manage data for the Pokemon detail screen.
 * @param navigationType The type of navigation (e.g., [NavigationType.BOTTOM_NAVIGATION] or [NavigationType.NAVIGATION_RAIL]).
 */
@Composable
fun PokemonDetailScreen(pokemonName : String, viewmodel : PokemonDetailViewModel = viewModel(factory = PokemonDetailViewModel.Factory), navigationType: NavigationType) {

    LaunchedEffect(pokemonName) {
        viewmodel.getPokemonDetail(pokemonName)
    }

    when(val pokemonDetailApiState = viewmodel.pokemonDetailApiState){
        is ApiPokemonDetailState.Error -> {
            Text("Error")
        }
        is ApiPokemonDetailState.Loading -> {
            Text("Loading")
        }
        is ApiPokemonDetailState.Success ->{
            pokemonDetailApiState.pokemonDetail?.let {
                if (navigationType == NavigationType.BOTTOM_NAVIGATION)
                    PokemonDetailScreenBottom(it)
                else
                    PokemonDetailScreenRail(it)

            }
        }
        else -> {
            Text("Error")
        }
    }

}