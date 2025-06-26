package org.mktech.tictactoe.data

import kotlinx.coroutines.flow.Flow
import org.mktech.tictactoe.models.GameState
import org.mktech.tictactoe.models.MakeTurn

interface RealTimeMessagingClient {
    fun getGameStateStream(): Flow<GameState>
    suspend fun sendAction(makeTurn: MakeTurn)
    suspend fun close()
}