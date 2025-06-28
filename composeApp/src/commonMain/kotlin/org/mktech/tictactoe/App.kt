package org.mktech.tictactoe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mktech.tictactoe.presentation.GameScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {

    val navController = rememberNavController()

    MaterialTheme { // Apply theme to the entire app
        NavHost(
            navController = navController,
            startDestination = AppRoutes.GAME_STATE,
        ) {
            composable(AppRoutes.GAME_STATE) {
                GameScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

object AppRoutes {
    const val GAME_STATE = "game_state"
}