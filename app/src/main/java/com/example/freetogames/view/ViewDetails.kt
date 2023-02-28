package com.example.freetogames.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freetogames.viewmodel.GamesViewModel

@Composable
fun DetailsScreen(gamesViewModel: GamesViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val game = gamesViewModel.selectedGame
        Text(text = game?.id.toString() ?: "Invalid id")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = game?.developer ?: "no developer")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = game?.gameUrl ?: "no url")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = game?.genre ?: "no genre")
    }
}