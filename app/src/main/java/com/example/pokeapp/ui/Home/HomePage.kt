package com.example.pokeapp.ui.Home

import android.util.DebugUtils
import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import com.example.pokeapp.model.Games.Generation
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pokeapp.model.Type.Type
import com.example.pokeapp.ui.components.NavigationType
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

/**
 * Composable function representing a card for displaying information about a generation.
 *
 * @param generation The [Generation] object containing information about the generation.
 * @param modifier Modifier for customizing the appearance of the card.
 */
@Composable
fun GenCard(generation : Generation, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    )
    {
        Column {
            AsyncImage(
                model = generation.image,
                contentDescription = generation.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
            Text(
                text = "${generation.name}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
    Divider(color = MaterialTheme.colorScheme.onSurface, thickness = 1.dp)

}
/**
 * Composable function representing a card for displaying information about a Pokemon type.
 *
 * @param type The [Type] object containing information about the Pokemon type.
 * @param modifier Modifier for customizing the appearance of the card.
 */
@Composable
fun TypeCard(type : Type, modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    )
    {
        Column {
            AsyncImage(
                model = type.image,
                contentDescription = type.type,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .width(120.dp)
            )
            Text(
                text = "${type.type}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
    Divider(color = MaterialTheme.colorScheme.onSurface, thickness = 1.dp)

}

/**
 * Composable function representing the main screen displaying generations and Pokemon types.
 *
 * @param generations List of [Generation] objects to be displayed.
 * @param types List of [Type] objects to be displayed.
 * @param navigationType The type of navigation (e.g., [NavigationType.BOTTOM_NAVIGATION] or [NavigationType.NAVIGATION_RAIL]).
 */
@Composable
fun screen(generations: List<Generation> , types: List<Type> , navigationType: NavigationType) {

    if (navigationType == NavigationType.BOTTOM_NAVIGATION) {
        Column {
            Text(
                text = "Generations:",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = Color(0xff000000),
                fontWeight = FontWeight.Bold,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                fontSize = 20.sp
            )
            LazyRow {
                items(generations) { generation ->
                    GenCard(generation = generation)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = Color.Gray)
                    .padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pokemon Types:",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = Color(0xff000000),
                fontWeight = FontWeight.Bold,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                fontSize = 20.sp
            )
            LazyRow {
                items(types) { type ->
                    TypeCard(type = type)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Generations:",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = Color(0xff000000),
                fontWeight = FontWeight.Bold,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                fontSize = 20.sp
            )
            LazyRow {
                items(generations) { generation ->
                    GenCard(generation = generation)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = Color.Gray)
                    .padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pokemon Types:",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = Color(0xff000000),
                fontWeight = FontWeight.Bold,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                fontSize = 20.sp
            )
            LazyRow {
                items(types) { type ->
                    TypeCard(type = type)
                }
            }
        }
    }
}



