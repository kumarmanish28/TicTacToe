package org.mktech.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mktech.tictactoe.data.bottomNavItems
import org.mktech.tictactoe.presentation.CustomBottomNavigationBar
import org.mktech.tictactoe.presentation.GameScreen
import org.mktech.tictactoe.theme.TicTacToeTheme
import org.mktech.tictactoe.theme.buttonColor
import org.mktech.tictactoe.theme.primary

@Composable
@Preview
fun App() {

    val navController = rememberNavController()

    TicTacToeTheme { // Apply theme to the entire app
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier,
                    onClick = {},
                    containerColor = buttonColor,
                    contentColor = primary,
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Text("Rule?")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = { CustomBottomNavigationBar(navController, bottomNavItems) }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = AppRoutes.GAME_STATE,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(AppRoutes.GAME_STATE) {
                    GameScreen(
                        modifier = Modifier.fillMaxSize().background(primary)
                    )
                }
                composable(AppRoutes.SETTINGS) {
                    Text("Settings Screen", modifier = Modifier.fillMaxSize())
                }
                composable(AppRoutes.PROFILE) {
                    Text("Profile Screen", modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

}

object AppRoutes {
    const val GAME_STATE = "game_state"
    const val SETTINGS = "settings"
    const val PROFILE = "profile"
}