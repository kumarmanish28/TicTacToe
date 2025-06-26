package org.mktech.tictactoe

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mktech.tictactoe.presentation.GameScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        GameScreen()
    }
}