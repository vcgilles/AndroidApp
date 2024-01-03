package com.example.pokeapp.ui.PokemonScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokeapp.model.Pokemon.PokemonDetail
import com.example.pokeapp.network.PokemonApi.ApiPokemonDetailState
import com.example.pokeapp.viewmodel.PokemonViewModel


@Composable
fun PokemonDetailScreen(pokemon : PokemonDetail) {
    Card(
        modifier = Modifier
            .padding(6.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
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
                    .size(110.dp)
                    .clip(CircleShape)
                    .fillMaxHeight()
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
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "${pokemon.height}",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "${pokemon.weight}",
                    modifier = Modifier.padding(16.dp)
                )

            }
        }

    }

}

@Composable
fun PokemonDetailScreen(pokemonName : String, viewmodel : PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)) {

    viewmodel.getPokemonDetail(pokemonName)

    when(val pokemonDetailApiState = viewmodel.pokemonDetailApiState){
        is ApiPokemonDetailState.Error -> {
            Text("Error")
        }
        is ApiPokemonDetailState.Loading -> {
            Text("Loading")
        }
        is ApiPokemonDetailState.Success ->{
            pokemonDetailApiState.pokemonDetail?.let { PokemonDetailScreen(it) }
        }
        else -> {
            Text("Error")
        }
    }

}