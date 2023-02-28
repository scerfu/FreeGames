@file:OptIn(ExperimentalMaterialApi::class)

package com.example.freetogames.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.freetogames.model.Games
import com.example.freetogames.model.GamesItem
import androidx.navigation.NavController

@Composable
fun GamesList(
    games: Games,
    navController: NavController,
    selectedGame: (GamesItem) -> Unit
) {
    LazyColumn {
        itemsIndexed(items = games) { index, gameItem ->
            GameItem(gamesItem = gameItem, navController, selectedGame)
        }
    }

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalUnitApi::class, ExperimentalFoundationApi::class)
@Composable
fun GameItem(
    gamesItem: GamesItem,
    navController: NavController? = null,
    selectedGame: ((GamesItem) -> Unit)? = null
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        onClick = {
            selectedGame?.invoke(gamesItem)
            navController?.navigate("details")
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = gamesItem.id.toString() ?: "Invalid id",
                fontSize = TextUnit(24F, TextUnitType.Sp),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle(1)
            )
            Text(text = gamesItem.gameUrl ?: "no email")
            Text(text = gamesItem.developer ?: "no address")
            Text(text = gamesItem.genre ?: "no phone number")
        }

    }
}
