package org.mktech.tictactoe.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject
import org.mktech.tictactoe.components.CustomDialog
import org.mktech.tictactoe.getPlatform
import org.mktech.tictactoe.theme.primary
import org.mktech.tictactoe.theme.textColor

@Composable
fun GameScreen(modifier: Modifier, viewmodel: TicTacToeViewModel = koinInject()) {
    val state by viewmodel.state.collectAsState()
    val isConnecting by viewmodel.isConnecting.collectAsState()
    val isShowConnectionError by viewmodel.isShowConnectionError.collectAsState()
    var isDialogShow by remember { mutableStateOf(false) }

    if (isShowConnectionError) {
        Box(
            modifier = modifier.fillMaxSize().background(primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Couldn't connect to the server",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
        return
    }
    Box(
        modifier = modifier.fillMaxSize()
            .background(primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(top = if (getPlatform().name == "iOS") 35.dp else 35.dp)
                .align(Alignment.TopCenter)
        ) {
            if (!state.connectedPlayer.contains('X')) {
                Text(text = "Waiting for player X", fontSize = 32.sp, color = textColor)
            } else if (!state.connectedPlayer.contains('O')) {
                Text(text = "Waiting for player O", fontSize = 32.sp, color = textColor)
            }
        }

        if (state.connectedPlayer.size == 2 && state.winningPlayer == null && !state.isBoardFull) {
            Text(
                text = if (state.playerAtTurn == 'X') "X is next" else "O is next",
                fontSize = 32.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = if (getPlatform().name == "iOS") 35.dp else 35.dp),
                color = textColor
            )
        }

        if (isConnecting) {
            Box(
                modifier = modifier.fillMaxSize()
                    .background(primary),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        TicTacToeField(
            state = state,
            onTapInField = viewmodel::finishTurn,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(16.dp)
        )
        if (state.isBoardFull || state.winningPlayer != null) {
            isDialogShow = true
            /*Text(
                text = when (state.winningPlayer) {
                    'X' -> "Player X won!"
                    'O' -> "Player O won!"
                    else -> "It's a draw!"
                },
                fontSize = 22.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            )*/
        }

        CustomDialog(
            showDialog = isDialogShow,
            title = "Status",
            msg = when (state.winningPlayer) {
                'X' -> "Player X won!"
                'O' -> "Player O won!"
                else -> "It's a draw!"
            },
            onDismissRequest = { isDialogShow = false },
        )

    }

}