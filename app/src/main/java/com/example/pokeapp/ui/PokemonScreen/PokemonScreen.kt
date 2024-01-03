package com.example.pokeapp.ui.PokemonScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokeapp.model.Pokemon.Pokemon


@Composable
fun PokemonCard(pokemon: Pokemon, onPokemonClick: (String) -> Unit) {
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
            Row( modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.Center) {
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.Center) {
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



@Composable
fun PokemonScreen(pokemons: List<Pokemon>, onPokemonClick: (String) -> Unit) {
    LazyColumn{
        items(pokemons) { p ->
            PokemonCard(pokemon = p , onPokemonClick)
        }
    }
}
