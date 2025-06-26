package org.mktech.tictactoe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.mktech.tictactoe.data.RealTimeMessagingClient
import org.mktech.tictactoe.models.GameState
import org.mktech.tictactoe.models.MakeTurn

class TicTacToeViewModel(private val client: RealTimeMessagingClient) : ViewModel() {

    val state = client
        .getGameStateStream()
        .onStart { _isConnecting.value = true }
        .onEach { _isConnecting.value = false }
        .catch { t -> _isShowConnectionError.value = t is ConnectTimeoutException }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GameState())

    private val _isConnecting = MutableStateFlow(false)
    val isConnecting = _isConnecting.asStateFlow()

    private val _isShowConnectionError = MutableStateFlow(false)
    val isShowConnectionError = _isShowConnectionError.asStateFlow()

    fun finishTurn(x: Int, y: Int) {
        if (state.value.field[y][x] != null || state.value.winningPlayer != null) {
            return
        }
        viewModelScope.launch {
            client.sendAction(MakeTurn(x, y))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            client.close()
        }
    }

}