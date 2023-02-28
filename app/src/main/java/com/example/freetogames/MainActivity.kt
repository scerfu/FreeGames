package com.example.freetogames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavController
import com.example.freetogames.ui.theme.FreeToGamesTheme
import com.example.freetogames.view.DetailsScreen
import com.example.freetogames.view.GamesList
import com.example.freetogames.viewmodel.GamesViewModel
import com.example.freetogames.viewmodel.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
//import org.koin.androidx.compose.koinViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: GamesViewModel by lazy {
        createViewModel()
    }

    private fun createViewModel(): GamesViewModel {
        return ViewModelProvider(this)[GamesViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreeToGamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val gamesViewModel = viewModel
                   // val gamesViewModel = koinViewModel<GamesViewModel>()
                    NavHost(navController = navController, startDestination = "main"){
                        composable(route = "main") {
                            MainScreen(gamesViewModel, navController = navController)
                        }
                        composable(route = "details") {
                            DetailsScreen(gamesViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    gamesViewModel: GamesViewModel,
    navController: NavController
) {
    when (val state = gamesViewModel.data.value) {
        is UIState.LOADING -> {}
        is UIState.SUCCESS -> {
            GamesList(state.response, navController) {
                gamesViewModel.selectedGame = it
            }
        }
        is UIState.FAILURE -> {}
        else ->{}
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FreeToGamesTheme {
        Greeting("Android")
    }
}