package org.mktech.tictactoe.models

import io.ktor.util.collections.ConcurrentMap
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class TicTacToe {

    private val state = MutableStateFlow(GameState())

    private val playerSockets = ConcurrentMap<Char, WebSocketSession>()

    private val gameScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var delayGameJob: Job? = null

    init {
        state.onEach(::broadCastState).launchIn(gameScope)
    }

    fun connectPlayer(session: WebSocketSession): Char? {
        val isPlayerX = state.value.connectedPlayer.any { it == 'X' }
        val player = if (isPlayerX) 'O' else 'X'

        state.update {
            if (state.value.connectedPlayer.contains(player)) {
                return null
            }
            if (!playerSockets.containsKey(player)) {
                playerSockets[player] = session
            }
            it.copy(
                connectedPlayer = it.connectedPlayer + player
            )
        }
        return player
    }

    fun disconnectPlayer(player: Char) {
        playerSockets.remove(player)
        state.update {
            it.copy(
                connectedPlayer = it.connectedPlayer - player
            )
        }
    }

    suspend fun broadCastState(gameState: GameState) {
        playerSockets.values.forEach { socket ->
            val json = Json.encodeToString(value = gameState)
            socket.send(
                Frame.Text(json)
            )
        }
    }

    fun finishTurn(player: Char, x: Int, y: Int) {
        if (state.value.field[y][x] != null || state.value.winningPlayer != null) {
            return
        }
        if (state.value.playerAtTurn != player) {
            return
        }
        val currentPlayer: Char? = state.value.playerAtTurn
        state.update {
            val newField = it.field.also { field ->
                field[y][x] = currentPlayer
            }
            val isBoardFull = newField.all { it.all { it != null } }

            if (isBoardFull) {
                startNewRoundDelayed()
            }
            it.copy(
                playerAtTurn = if (currentPlayer == 'X') 'O' else 'X',
                field = newField,
                isBoardFull = isBoardFull,
                winningPlayer = getWinningPlayer()?.also {
                    startNewRoundDelayed()
                }
            )
        }
    }

    private fun getWinningPlayer(): Char? {
        val field = state.value.field
        return if (field[0][0] != null && field[0][0] == field[0][1] && field[0][1] == field[0][2]) {
            field[0][0]
        } else if (field[1][0] != null && field[1][0] == field[1][1] && field[1][1] == field[1][2]) {
            field[1][0]
        } else if (field[2][0] != null && field[2][0] == field[2][1] && field[2][1] == field[2][2]) {
            field[2][0]
        } else if (field[0][0] != null && field[0][0] == field[1][0] && field[1][0] == field[2][0]) {
            field[0][0]
        } else if (field[0][1] != null && field[0][1] == field[1][1] && field[1][1] == field[2][1]) {
            field[0][1]
        } else if (field[0][2] != null && field[0][2] == field[1][2] && field[1][2] == field[2][2]) {
            field[0][2]
        } else if (field[0][0] != null && field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            field[0][0]
        } else if (field[0][2] != null && field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            field[0][2]
        } else null
    }

    private fun startNewRoundDelayed() {
        delayGameJob?.cancel()
        delayGameJob = gameScope.launch {
            delay(5000L)
            state.update {
                it.copy(
                    playerAtTurn = 'X',
                    field = GameState.emptyField(),
                    isBoardFull = false,
                    winningPlayer = null
                )
            }
        }
    }

}

