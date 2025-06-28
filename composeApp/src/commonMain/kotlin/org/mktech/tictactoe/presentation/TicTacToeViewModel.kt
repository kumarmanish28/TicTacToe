package org.mktech.tictactoe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.network.sockets.ConnectTimeoutException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
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
        print("onCleared1111111111111111111111111111111111111111111111111111111111")
        viewModelScope.launch {
            client.close()
        }
    }

}